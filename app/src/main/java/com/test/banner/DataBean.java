package com.test.banner;

import java.util.ArrayList;
import java.util.List;

public class DataBean {
    public Integer imageRes;
    public String imageUrl;
    public String title;

    public DataBean(Integer imageRes, String title) {
        this.imageRes = imageRes;
        this.title = title;
    }

    public DataBean(String imageUrl, String title) {
        this.imageUrl = imageUrl;
        this.title = title;
    }

    public static List<DataBean> getTestData() {
        List<DataBean> list = new ArrayList<>();
        list.add(new DataBean(R.drawable.b1, "这是第一张图片"));
        list.add(new DataBean(R.drawable.b2, "这是第二张图片"));
        list.add(new DataBean(R.drawable.b3, "这是第三张图片"));
        return list;
    }
    public static List<DataBean> getTestData2() {
        List<DataBean> list = new ArrayList<>();
        list.add(new DataBean(R.drawable.a, "这是第一张图片!"));
        list.add(new DataBean(R.drawable.b, "这是第二张图片!"));
        return list;
    }
}
