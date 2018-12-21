package com.example.googleplay74.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * 关闭流的工具类，可以不用抛异常
 */
public class IOUtil {
    public static void close(Closeable c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
