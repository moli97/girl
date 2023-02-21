package top.imoli.girl.util;

import com.alibaba.fastjson.JSONObject;
import javafx.util.Pair;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @author moli@hulai.com
 * @date 2023/2/21 3:41 PM
 */
public class ProxyPoolUtil {

    public static final int TRY_COUNT = 10;

    public static Document getProxyDocument(Connection connection) throws IOException {
        Connection bak = connection.newRequest();
        int count = 0;
        while (true) {
            try {
                JSONObject proxy = getProxy();
                String proxyStr = proxy.getString("proxy");
                String[] split = proxyStr.split(":");
                boolean https = proxy.getBooleanValue("https");
                return connection.proxy((https ? "https" : "http") + "://" + split[0], Integer.parseInt(split[1])).get();
            } catch (Exception e) {
                e.printStackTrace();
                count++;
                if (count > TRY_COUNT) {
                    return bak.get();
                }
            }
        }
    }

    public static JSONObject getProxy() throws IOException {
        Document document = Jsoup.connect("http://127.0.0.1:5010/get/")
                .header("Content-Type", "application/json")
                .ignoreContentType(true)
                .get();
        return JSONObject.parseObject(document.body().text());
    }

    public static Pair<String, Integer> getProxyPair() {
        int count = 0;
        while (true) {
            try {
                JSONObject proxy = getProxy();
                if (proxy == null || !proxy.containsKey("proxy")) {
                    continue;
                }
                String proxyStr = proxy.getString("proxy");
                String[] split = proxyStr.split(":");
                if (split.length != 2) {
                    continue;
                }
                return new Pair<>(split[0], Integer.valueOf(split[1]));
            } catch (Exception e) {
                count++;
                if (count > 10) {
                    return null;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 10; i++) {
            Pair<String, Integer> proxyPair = getProxyPair();
            System.out.println(proxyPair.getKey() + ":" + proxyPair.getValue());
        }
    }
}
