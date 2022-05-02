package top.imoli.girl.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * @author moli
 * @date 2022/5/2 2:27
 */
public class DownloadUtil {

    public static void download(String url, String path, AtomicInteger count, boolean incrFileName) {
        download(url, path, count, incrFileName, null);
    }

    public static void download(String url, String path, AtomicInteger count, boolean incrFileName, Function<String, String> fileNameFunc) {
        try {
            String fileName;
            int i = 0;
            if (count != null) {
                i = count.incrementAndGet();
            } else {
                incrFileName = false;
            }
            path = mkdir(path);
            if (fileNameFunc != null) {
                fileName = path + File.separator + fileNameFunc.apply(url);
            } else if (incrFileName) {
                fileName = path + File.separator + i + ".jpg";
            } else {
                fileName = path + File.separator + url.substring(url.lastIndexOf("/") + 1);
            }
            File file = new File(fileName);
            if (file.exists()) {
                return;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            URL target = new URL(url);
            // 获取连接对象
            URLConnection urlConnection = target.openConnection();
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.127 Mobile Safari/537.36");
            // 获取输入流，用来读取图片信息
            InputStream inputStream = urlConnection.getInputStream();
            // 获取输出流  输出地址+文件名

            int len;
            // 设置一个缓存区
            byte[] buffer = new byte[1024 * 1024];
            // 写出图片到E:\JsoupPic中,  输入流读数据到缓冲区中，并赋给len
            while ((len = inputStream.read(buffer)) > 0) {
                // 参数一：图片数据  参数二：起始长度  参数三：终止长度
                fileOutputStream.write(buffer, 0, len);
            }
            // 关闭输入输出流 最后创建先关闭
            fileOutputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String mkdir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!file.isDirectory()) {
            throw new RuntimeException("不是文件夹");
        }
        return file.getPath();
    }
}
