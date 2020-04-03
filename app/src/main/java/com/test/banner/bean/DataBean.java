package com.test.banner.bean;

import com.test.banner.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataBean {
    public Integer imageRes;
    public String imageUrl;
    public String title;
    public int viewType;

    public DataBean(Integer imageRes, String title, int viewType) {
        this.imageRes = imageRes;
        this.title = title;
        this.viewType = viewType;
    }

    public DataBean(String imageUrl, String title, int viewType) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.viewType = viewType;
    }

    public static List<DataBean> getTestData() {
        List<DataBean> list = new ArrayList<>();
        list.add(new DataBean(R.drawable.image1, "相信自己,你努力的样子真的很美", 1));
        list.add(new DataBean(R.drawable.image2, "极致简约,梦幻小屋", 3));
        list.add(new DataBean(R.drawable.image3, "超级卖梦人", 3));
        list.add(new DataBean(R.drawable.image4, "夏季新搭配", 1));
        list.add(new DataBean(R.drawable.image5, "绝美风格搭配", 1));
        list.add(new DataBean(R.drawable.image6, "微微一笑 很倾城", 3));
        return list;
    }

    public static List<DataBean> getTestData2() {
        List<DataBean> list = new ArrayList<>();
        list.add(new DataBean(R.drawable.image7, "听风.赏雨", 3));
        list.add(new DataBean(R.drawable.image8, "迪丽热巴.迪力木拉提", 1));
        list.add(new DataBean(R.drawable.image9, "爱美.人间有之", 3));
        list.add(new DataBean(R.drawable.image10, "洋洋洋.气质篇", 1));
        list.add(new DataBean(R.drawable.image11, "生活的态度", 3));
        return list;
    }

    /**
     * 仿淘宝商品详情第一个是视频
     * @return
     */
    public static List<DataBean> getTestDataVideo() {
        List<DataBean> list = new ArrayList<>();
        list.add(new DataBean("http://vfx.mtime.cn/Video/2019/03/09/mp4/190309153658147087.mp4", "第一个放视频", 2));
        list.add(new DataBean(R.drawable.image7, "听风.赏雨", 1));
        list.add(new DataBean(R.drawable.image8, "迪丽热巴.迪力木拉提", 1));
        list.add(new DataBean(R.drawable.image9, "爱美.人间有之", 1));
        list.add(new DataBean(R.drawable.image10, "洋洋洋.气质篇", 1));
        list.add(new DataBean(R.drawable.image11, "生活的态度", 1));
        return list;
    }

    public static List<DataBean> getTestData3() {
        List<DataBean> list = new ArrayList<>();
        list.add(new DataBean("https://img.zcool.cn/community/011ad05e27a173a801216518a5c505.jpg", null, 1));
        list.add(new DataBean("https://img.zcool.cn/community/0148fc5e27a173a8012165184aad81.jpg", null, 1));
        list.add(new DataBean("https://img.zcool.cn/community/013c7d5e27a174a80121651816e521.jpg", null, 1));
        list.add(new DataBean("https://img.zcool.cn/community/01b8ac5e27a173a80120a895be4d85.jpg", null, 1));
        list.add(new DataBean("https://img.zcool.cn/community/01a85d5e27a174a80120a895111b2c.jpg", null, 1));
        list.add(new DataBean("https://img.zcool.cn/community/01085d5e27a174a80120a8958791c4.jpg", null, 1));
        list.add(new DataBean("https://img.zcool.cn/community/01f8735e27a174a8012165188aa959.jpg", null, 1));
        return list;
    }

    public static List<String> getColors(int size) {
        List<String> list = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            list.add(getRandColor());
        }
        return list;
    }

    /**
     * 获取十六进制的颜色代码.例如  "#5A6677"
     * 分别取R、G、B的随机值，然后加起来即可
     *
     * @return String
     */
    public static String getRandColor() {
        String R, G, B;
        Random random = new Random();
        R = Integer.toHexString(random.nextInt(256)).toUpperCase();
        G = Integer.toHexString(random.nextInt(256)).toUpperCase();
        B = Integer.toHexString(random.nextInt(256)).toUpperCase();

        R = R.length() == 1 ? "0" + R : R;
        G = G.length() == 1 ? "0" + G : G;
        B = B.length() == 1 ? "0" + B : B;

        return "#" + R + G + B;
    }
}
