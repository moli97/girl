package top.imoli.girl.controller;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import top.imoli.girl.util.DownloadUtil;
import top.imoli.girl.util.ProgressUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author moli
 * @date 2022/5/4 0:42
 */
public class Test {

    public static void main(String[] args) throws IOException, InterruptedException {
        String s = FileUtils.readFileToString(new File("E:\\opt\\projects\\idea\\girl\\src\\main\\java\\top\\imoli\\girl\\controller\\test.html"));
        Document parse = Jsoup.parse(s);
        ExecutorService executor = Executors.newFixedThreadPool(24);
        Elements elements = parse.select(".spotlight-group > .spotlight");
        CountDownLatch count = new CountDownLatch(elements.size() * 2);
        AtomicInteger integer = new AtomicInteger();
        ProgressUtil.print("", "下载中", elements.size() * 2, integer);
        for (Element element : elements) {
            executor.execute(() -> {
                DownloadUtil.download(element.attr("data-src"), "C:\\Users\\moli\\Desktop\\2\\cdn", integer, false);
                count.countDown();
            });
            executor.execute(() -> {
                DownloadUtil.download(element.selectFirst("img").attr("src"), "C:\\Users\\moli\\Desktop\\2\\tt", integer, false);
                count.countDown();
            });
        }
        count.await();
        System.out.println("下载完成");
        executor.shutdown();
    }
}
