package com.example.googleplay74.domain;

/**
 * 分类Javabean
 */
public class CateGoryInfo {
    public String name1;
    public String name2;
    public String name3;
    public String url1;
    public String url2;
    public String url3;

    public String title;// 分类标题
    public boolean isTitle;// 标记是否是标题

    @Override
    public String toString() {
        return "CateGoryInfo{" +
                "name1='" + name1 + '\'' +
                ", name2='" + name2 + '\'' +
                ", name3='" + name3 + '\'' +
                ", url1='" + url1 + '\'' +
                ", url2='" + url2 + '\'' +
                ", url3='" + url3 + '\'' +
                ", title='" + title + '\'' +
                ", isTitle=" + isTitle +
                '}';
    }
}
