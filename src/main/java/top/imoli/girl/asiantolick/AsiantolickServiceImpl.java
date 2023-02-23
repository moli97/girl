package top.imoli.girl.asiantolick;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import top.imoli.girl.asiantolick.entity.Asiantolick;

import java.io.IOException;

/**
 * @author moli@hulai.com
 * @date 2023/2/23 10:17 AM
 */
public class AsiantolickServiceImpl {

    public static void main(String[] args) throws IOException {
        parseWithIndex(1);
    }

    public static void parseWithIndex(int index) throws IOException {
        String url = "https://asiantolick.com/ajax/buscar_posts.php?post=&cat=&tag=&search=&page=&index=" + index + "&ver=98";
        Document document = Jsoup.connect(url).proxy("127.0.0.1", 7890).get();
        Elements elements = document.select("a");
        elements.forEach(AsiantolickServiceImpl::parseAndSave);
    }

    private static void parseAndSave(Element element) {
        String href = element.attr("abs:href");
        String postId = element.selectFirst("a > div > img").attr("post-id");
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
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
