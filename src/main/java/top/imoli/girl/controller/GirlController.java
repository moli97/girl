package top.imoli.girl.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.imoli.girl.entity.Album;
import top.imoli.girl.entity.Girl;
import top.imoli.girl.mapper.AlbumMapper;
import top.imoli.girl.service.IGirlService;
import top.imoli.girl.util.DownloadUtil;
import top.imoli.girl.util.ProgressUtil;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author moli
 * @date 2022/5/1 18:46
 */
@RestController
@RequestMapping("/girl")
public class GirlController {

    @Autowired
    private Executor globalExecutor;
    @Autowired
    private IGirlService girlService;
    @Autowired
    private AlbumMapper albumMapper;
    @Autowired
    private Executor downloadExecutor;

    @GetMapping("/parse")
    public String handleGirl(@RequestParam(name = "start", defaultValue = "1") int start, @RequestParam(name = "end", defaultValue = "999") int end, @RequestParam(name = "sync", defaultValue = "true") boolean sync) {
        for (int i = start; i <= end; i++) {
            if (sync) {
                if (!girlService.parseGirl(i)) {
                    return "在第" + i + "页解析失败";
                }
                System.out.println("第" + i + "页解析成功");
            } else {
                int page = i;
                globalExecutor.execute(() -> girlService.parseGirl(page));
            }
        }
        return "ok";
    }

    @GetMapping("/parseAlbum")
    public String parseAlbum(@RequestParam(name = "start", defaultValue = "10000") int start, @RequestParam(name = "end", defaultValue = "9999999") int end, @RequestParam(name = "sync", defaultValue = "false") boolean sync, @RequestParam(name = "limit", defaultValue = "9999999") int limit) {
        QueryWrapper<Girl> wrapper = new QueryWrapper<>();
        wrapper.ge("id", start).le("id", end).eq("status", 0).last(" limit " + limit);
        List<Girl> list = girlService.list(wrapper);
        for (Girl girl : list) {
            if (sync) {
                if (!girlService.parseAlbum(girl.getId())) {
                    return "解析失败 girlId:" + girl.getId();
                }
            } else {
                globalExecutor.execute(() -> girlService.parseAlbum(girl.getId()));
            }
        }
        return list.size() + "个girl正在解析";
    }

    @GetMapping("/down")
    public String down(@RequestParam(name = "start", defaultValue = "10000") int start, @RequestParam(name = "end", defaultValue = "9999999") int end, @RequestParam(name = "sync", defaultValue = "false") boolean sync, @RequestParam(name = "limit", defaultValue = "9999999") int limit) {
        QueryWrapper<Girl> wrapper = new QueryWrapper<>();
        wrapper.ge("id", start).le("id", end).eq("status", 1).last(" limit " + limit);
        List<Girl> list = girlService.list(wrapper);
        return down(list, sync);
    }

    @GetMapping("/downHits")
    public String down(@RequestParam(name = "sync", defaultValue = "false") boolean sync, @RequestParam(name = "limit", defaultValue = "10") int limit) {
        QueryWrapper<Girl> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1).last(" order by (hits + 0) desc limit " + limit);
        List<Girl> list = girlService.list(wrapper);
        return down(list, sync);
    }

    private String down(List<Girl> list, boolean sync) {
        for (Girl girl : list) {
            if (sync) {
                if (!downGirl(girl)) {
                    return "下载失败 girlId:" + girl.getId();
                }
            } else {
                globalExecutor.execute(() -> downGirl(girl));
            }
        }
        return list.size() + "个girl正在下载";
    }

    private boolean downGirl(Girl girl) {
        if (girl.getStatus() != 1) {
            System.out.println("status error: " + girl.getStatus());
            return false;
        }
        try {
            QueryWrapper<Album> wrapper = new QueryWrapper<>();
            wrapper.eq("girlId", girl.getId()).ne("status", 1).ge("size", 0);
            List<Album> albums = albumMapper.selectList(wrapper);
            int total = albums.stream().mapToInt(Album::getSize).sum();
            if (total == 0) {
                return true;
            }
            AtomicInteger count = new AtomicInteger();
            ProgressUtil.print("", girl.getGirlName() + " 下载中: ", total, count);
            CountDownLatch downLatch = new CountDownLatch(albums.size());
            for (Album album : albums) {
                globalExecutor.execute(() -> downAlbum(album, count, downLatch));
            }
            downLatch.await();
            girl.setStatus(2);
            girlService.saveOrUpdate(girl);
            System.out.println("下载成功 girlId:" + girl.getId() + "\t 文件夹: " + FOLDER + girl.getId());
        } catch (Exception e) {
            System.out.println("下载失败 girlId:" + girl.getId());
            return false;
        }
        return true;
    }

    private static final String IMG_URL = "https://img.xsnvshen.co/album/";
    private static final String FOLDER = "E:\\data\\image\\girl\\";

    private void downAlbum(Album album, AtomicInteger count, CountDownLatch downLatch) {
        CountDownLatch downLatch1 = new CountDownLatch(album.getSize());
        try {
            AtomicBoolean flag = new AtomicBoolean(true);
            for (int i = 0; i < album.getSize(); i++) {
                String fileId = pad(3, i);
                downloadExecutor.execute(() -> {
                    try {
                        String url = IMG_URL + album.getGirlId() + "/" + album.getId() + "/" + fileId + ".jpg";
                        DownloadUtil.download(url, FOLDER + album.getGirlId() + "\\" + album.getId(), count, false);
                    } catch (Exception e) {
                        e.printStackTrace();
                        flag.set(false);
                    } finally {
                        downLatch1.countDown();
                    }
                });
            }
            downLatch1.await();
            if (flag.get()) {
                album.setStatus(1);
                albumMapper.updateById(album);
            } else {
                System.out.println("下载失败 albumId:" + album.getId());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            downLatch.countDown();
        }

    }

    public static String pad(int length, long num) {
        return String.format("%0".concat(String.valueOf(length)).concat("d"), num);
    }
}
