package top.imoli.girl.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import top.imoli.girl.entity.Album;
import top.imoli.girl.entity.Girl;
import top.imoli.girl.entity.GirlAttribute;
import top.imoli.girl.mapper.AlbumMapper;
import top.imoli.girl.mapper.GirlMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * @author moli
 * @date 2022/5/1 19:21
 */
@Service
public class IGirlServiceImpl extends ServiceImpl<GirlMapper, Girl> implements IGirlService {

    private static final Elements EMPTY = new Elements();
    private static final String BASE_URL = "https://www.xsnvshen.co";
    private static final String URL0 = "https://www.xsnvshen.co/girl/?p=";
    private static final String URL1 = "https://www.xsnvshen.co/girl/";

    @Value("${girl.cookie}")
    private String cookie;
    @Value("${girl.userAgent}")
    private String userAgent;
    @Value("${girl.existStillSave:false}")
    private boolean existStillSave;
    @Autowired
    private AlbumMapper albumMapper;
    private Map<String, String> cookies;


    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        String[] cookieArr = cookie.split(";");
        cookies = new HashMap<>(cookieArr.length);
        for (String s : cookieArr) {
            String[] split = s.split("=");
            if (split.length == 2) {
                cookies.put(split[0], split[1]);
            }
        }
    }

    @Override
    public boolean parseGirl(int page) {
        try {
            Document document = getDocument(URL0 + page);
            Elements elements = document.select(".itemimg");
            elements.forEach(this::parseAndSave);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private Document getDocument(String url) throws IOException {
        return Jsoup.connect(url).userAgent(userAgent).cookies(cookies).get();
    }

    private void parseAndSave(Element element) {
        String href = element.attr("abs:href");
        try {
            if (!existStillSave) {
                int id = parseId(href);
                Girl girl = this.getById(id);
                if (girl != null) {
                    return;
                }
            }
            Document document = getDocument(href);
            Girl girl = parseGirl(document).build();
            saveOrUpdate(girl);
        } catch (Exception e) {
            System.out.println("解析错误: " + href);
            System.out.println(e.getMessage());
        }
    }

    public static String removeNonBmpUnicode(String str) {
        return null == str ? null : str.replaceAll("[^\\u0000-\\uFFFF]", "");
    }

    private static int parseId(String href) {
        return Integer.parseInt(href.substring(href.lastIndexOf("/") + 1));
    }

    private static Girl.Builder parseGirl(Document document) {
        Girl.Builder builder = Girl.newBuilder();
        String href = document.selectFirst("link[rel='canonical']").attr("href");
        builder.id(parseId(href));
        Element baseInfo = document.selectFirst("#entry-mod-baseInfo");
        setBaseInfo(baseInfo, builder);
        builder.introduction(removeNonBmpUnicode(document.selectFirst(".star2-intro-bd > p").text()));
        Elements elements = document.select(".create");
        if (elements.size() >= 2) {
            builder.createTime(elements.get(0).text());
            builder.hits(elements.get(1).text());
        }
        builder.cover(document.selectFirst(".rihgtbkimg > img").attr("src"));
        return builder;
    }

    private static void setBaseInfo(Element baseInfo, Girl.Builder builder) {
        Elements titles = baseInfo.select(".bas-title");
        Elements contents = baseInfo.select(".bas-cont");
        if (titles.size() != contents.size()) {
            throw new RuntimeException("titles.size() != contents.size() ---" + titles.size() + " " + contents.size());
        }
        for (int i = 0; i < titles.size(); i++) {
            String title = titles.get(i).text();
            String content = contents.get(i).text();
            GirlAttribute attribute = GirlAttribute.of(title);
            if (attribute == null) {
                System.out.println("未知属性：" + title);
                continue;
            }
            attribute.setValue(builder, content);
        }
    }

    @Override
    public boolean parseAlbum(int girlId) {
        try {
            Girl girl = this.getById(girlId);
            if (girl == null || girl.getStatus() > 0) {
                return false;
            }
            Document parse = getDocument(URL1 + girlId);
            Element element = parse.selectFirst(".entryAblum > .star-mod-bd");
            if (element == null) {
                System.out.println("解析完成(没有图集)：" + girlId);
                girl.setStatus(-1);
                saveOrUpdate(girl);
                return false;
            }
            Elements elements = element.select("ul > li > a");
            for (Element e : elements) {
                parseAlbumAndSave(e, girlId);
            }
            System.out.println("解析完成：" + girlId);
            girl.setStatus(1);
            saveOrUpdate(girl);
            return true;
        } catch (Exception e) {
            System.out.println("解析错误: " + girlId);
            System.out.println(e.getMessage());
        }
        return false;
    }

    private void parseNewsAndSave(Element element, int girlId) {

    }

    private void parseAlbumAndSave(Element element, int girlId) {
        String href = element.attr("href");
        int id = parseId(href);
        try {
            Album album = albumMapper.selectById(id);
            boolean has = album != null;
            if (has && album.getSize() > 0) {
                return;
            }
            String title = element.attr("title");
            Element img = element.selectFirst("img");
            String cover = "";
            if (img != null) {
                cover = img.attr("data-load");
            }
            album = Album.newBuilder().girlId(girlId).id(id).title(title).cover(cover).size(albumImages(BASE_URL + href).size()).build();
            if (has) {
                albumMapper.updateById(album);
            } else {
                albumMapper.insert(album);
            }
        } catch (Exception e) {
            System.out.println("parseAlbumAndSave error: " + girlId + ":" + id);
            throw new RuntimeException(e);
        }
    }

    private Elements albumImages(String url) {
        try {
            Document document = getDocument(url);
            return document.select(".gallery > li > div > img");
        } catch (IOException e) {
            return EMPTY;
        }
    }
}
