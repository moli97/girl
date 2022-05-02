package top.imoli.girl.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author moli
 * @date 2022/5/2 2:55
 */
public class ProgressUtil {

    private static final boolean ASYNC = false;
    private static final int MAX_INDEX = 50;

    public static void print(String name, String prefix, int total, AtomicInteger progress) {
        new Thread(() -> {
            StringBuilder kg = new StringBuilder();
            for (int i = 0; i < MAX_INDEX; i++) {
                kg.append(" ");
            }
            String printPrefix = ASYNC ? "《" + name + "》 " + prefix : prefix;
            System.out.print(printPrefix + "00%[>" + kg + "]");
            while (true) {
                int prog = progress.get();
                int count = prog * 100 / total;
                printCurrentNum(prog, total, count, printPrefix);
                if (count >= 100) {
                    break;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println();
        }).start();
    }

    private static void focusGoto(int length) {
        if (ASYNC) {
            System.out.println();
        } else {
            for (int i = MAX_INDEX + length + 10; i > 0; i--) {
                System.out.print('\b');
            }
        }
    }

    private static void printCurrentNum(int progress, int total, int percentage, String prefix) {
        String num = "000" + percentage;
        num = num.substring(num.length() - String.valueOf(percentage).length());
        StringBuffer s = new StringBuffer(prefix + "[" + progress + "/" + total + "]  " + num + "%[");
        focusGoto(s.length());
        int per = (percentage * 100) / 100;
        for (int index = 0; index < MAX_INDEX; index++) {
            int c = (index * 100) / MAX_INDEX;
            if (c < per) {
                s.append("■");
            } else {
                s.append(" ");
            }
        }
        s.append("]");
        System.out.print(s);
    }
}
