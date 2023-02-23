package top.imoli.girl.asiantolick;

import java.io.IOException;

/**
 * @author moli@hulai.com
 * @date 2023/2/23 10:09 AM
 */
public class Test {

    public static void main(String[] args) throws IOException {
        String s = "link=https://cdn2.asiantolick.com/temp_dl/TricolourLovestoryCosplay(UNCENSORED)_392_AsianToLick_com.ziphttps://cdn2.asiantolick.com/temp_dl/TricolourLovestoryCosplay(UNCENSORED)_392_AsianToLick_com.zip";
        int indexOf = s.lastIndexOf("http");
        String substring = s.substring(indexOf);
        System.out.println(substring);

    }
}
