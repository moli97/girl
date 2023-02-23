package top.imoli.girl.asiantolick;

import java.io.IOException;

/**
 * @author moli@hulai.com
 * @date 2023/2/23 10:09 AM
 */
public class Test {

    public static void main(String[] args) throws IOException {
        String url = "https://asiantolick.com/download-3684/download";
        String downloadUrl = AsiantolickServiceImpl.getDownloadUrl(url);
        String resources = AsiantolickServiceImpl.getResources(downloadUrl);
        System.out.println(resources);

    }
}
