#Android图片轮播控件  
<br>
现在的绝大数app都有banner界面，实现循环播放多个广告图片和手动滑动循环等功能。因为ViewPager并不支持循环翻页，
所以要实现循环还得需要自己去动手，所以其他的轮播控件大多都是重写viewpager，而且代码很臃肿。
我就把项目中的控件剔了出来，希望大家觉得有用。
## 效果图
![效果示例](https://raw.githubusercontent.com/youth5201314/banner/master/image/banner.png)
##Gradle
```groovy
dependencies{
    compile 'com.youth:banner:1.0.1'
}
```
##方法
|方法名|参数|描述
|:---:|:---:|:---:|
|setDelayTime|int(时间)| 设置轮播图片间隔时间（默认为2000）
|setImages|url/resources| 设置轮播图片 
|setOnBannerClickListener|/|设置点击事件

##使用步骤

####1.在布局文件中添加Banner
```xml
<com.youth.banner.Banner
    android:id="@+id/banner"
    android:layout_width="match_parent"
    android:layout_height="200dp" />
```
#### 3.在Activity或者Fragment中配置Banner 
```java
private Banner banner;
String[] images= new String[] {"url"};
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    banner = (Banner) findViewById(R.id.banner);
    banner.setDelayTime(500);//设置轮播间隔时间
    banner.setImages(images);//可以选择设置图片网址，或者资源文件
    banner.setOnBannerClickListener(new Banner.OnBannerClickListener() {//设置点击事件
        @Override
        public void OnBannerClick(View view, int position) {

        }
    });

}
```

