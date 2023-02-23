package top.imoli.girl.asiantolick;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author moli@hulai.com
 * @date 2023/2/23 10:09 AM
 */
public class Test {

    public static void main(String[] args) throws IOException {
        int index = 1;
        String url = "https://asiantolick.com/ajax/buscar_posts.php?post=&cat=&tag=&search=&page=&index=" + index + "&ver=98";
        //url = "http://10.0.67.99/opt/project/girl/src/main/java/top/imoli/girl/asiantolick/index.html";
        Document document = Jsoup.connect(url).proxy("127.0.0.1", 7890).get();
        Elements elements = document.select("a");

    }
}
