package top.imoli.girl.asiantolick;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import top.imoli.girl.asiantolick.entity.Asiantolick;
import top.imoli.girl.mapper.AsiantolickMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author moli@hulai.com
 * @date 2023/2/23 10:17 AM
 */
@Service
public class AsiantolickServiceImpl extends ServiceImpl<AsiantolickMapper, Asiantolick> implements IService<Asiantolick> {

    @EventListener(ApplicationReadyEvent.class)
    public void init() throws Exception {
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2,SSLv3");
        for (int i = 0; i < 20; i++) {
            parseWithIndex(i);
        }
    }

    public void parseWithIndex(int index) throws Exception {
        String url = "https://asiantolick.com/ajax/buscar_posts.php?post=&cat=&tag=&search=&page=&index=" + index + "&ver=98";
        Document document = Jsoup.connect(url).proxy("127.0.0.1", 7890).get();
        Elements elements = document.select("a");
        elements.forEach(this::parseAndSave);
    }

    private void parseAndSave(Element element) {
        String postId = element.selectFirst("a > div > img").attr("post-id");
        Asiantolick byId = this.getById(postId);
        if (byId != null) {
            return;
        }
        String href = element.attr("abs:href");
        String alt = element.selectFirst("a > div > img").attr("alt");
        String numberPhotos = element.select("a > div").get(1).text();
        System.out.println("href = " + href);
        System.out.println("postId = " + postId);
        System.out.println("alt = " + alt);
        System.out.println("numberPhotos = " + numberPhotos);
        Asiantolick.Builder builder = Asiantolick.Builder.newBuilder()
                .postId(Integer.parseInt(postId))
                .href(href)
                .alt(alt)
                .size(Integer.parseInt(numberPhotos));
        try {
            Document document = Jsoup.connect(href).proxy("127.0.0.1", 7890).get();
            Thread.sleep(1000);
            Elements elements = document.select("#metadata_qrcode > div > span");
            List<String> collect = elements.stream().map(Element::text).collect(Collectors.toList());

            String description = getProperty(collect, "description:");
            String galleryPictures = getProperty(collect, "Gallery pictures:");
            String creationDate = getProperty(collect, "Creation date:");
            String photosSize = getProperty(collect, "Photos size:");
            String albumSize = getProperty(collect, "Album size:");

            String download = document.select("#metadata_qrcode > div > span > a").attr("abs:href");
            builder.downHref(download)
                    .creationDate(creationDate)
                    .photosSize(photosSize)
                    .albumSize(albumSize)
                    .description(description)
                    .galleryPictures(galleryPictures);
            Asiantolick asiantolick = builder.build();
            saveOrUpdate(asiantolick);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(List<String> collect, String keyWord) {
        for (String s : collect) {
            if (s.contains(keyWord)) {
                return s.substring(keyWord.length()).trim();
            }
        }
        return "";
    }
}
