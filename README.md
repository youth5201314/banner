# Android图片轮播控件 (如果对你有帮助请star哦！) 
<br>
现在的绝大数app都有banner界面，实现循环播放多个广告图片和手动滑动循环等功能。因为ViewPager并不支持循环翻页，
所以要实现循环还得需要自己去动手，所以其他的轮播控件大多都是重写viewpager，而且代码很臃肿。
我就把项目中的控件剔了出来，希望大家觉得有用。
## 效果图
|模式|图片
|---|---|
|默认指示器模式|![效果示例](https://raw.githubusercontent.com/youth5201314/banner/master/image/1.png)
|数字模式|![效果示例](https://raw.githubusercontent.com/youth5201314/banner/master/image/2.png)
|数字加标题模式|![效果示例](https://raw.githubusercontent.com/youth5201314/banner/master/image/3.png)
|指示器加标题模式|![效果示例](https://raw.githubusercontent.com/youth5201314/banner/master/image/4.png)

### 联系方式
* 邮箱地址： 1028729086@qq.com 
* 如果遇到问题和建议欢迎在给我发送邮件，希望让这个工程越来越完善。

##Gradle
```groovy
dependencies{
    compile 'com.youth.banner:banner:1.1.0'  //指定版本
    compile 'com.youth.banner:banner:+' //最新版本
}
```
或者引用本地lib
```groovy
compile project(':banner')
```
##方法
|方法名|描述
|---|---|
|setBannerStyle(int bannerStyle)| 设置轮播样式（默认为Banner.CIRCLE_INDICATOR）
|setBannerTitle(String[] titles)| 设置轮播要显示的标题和图片对应（如果不传默认不显示标题）
|setDelayTime(int time)| 设置轮播图片间隔时间（默认为2000）
|setImages(Object[] imagesUrl)| 设置轮播图片 
|setOnBannerClickListener(OnBannerClickListener listener)|设置点击事件
## 常量
|方法名|描述
|---|---|
|Banner.NOT_INDICATOR| 不显示指示器和标题
|Banner.CIRCLE_INDICATOR| 显示圆形指示器
|Banner.NUM_INDICATOR| 显示数字指示器
|Banner.NUM_INDICATOR_TITLE| 显示数字指示器和标题
|Banner.CIRCLE_INDICATOR_TITLE| 显示圆形指示器和标题
##使用步骤

####1.在布局文件中添加Banner
```xml
<com.youth.banner.Banner
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/banner"
    android:layout_width="match_parent"
    android:layout_height="高度自己设置"
    //下面为自定义设置，可以不设置全部默认样式
    app:indicator_margin="指示器之间的间距"
    app:indicator_drawable_selected="指示器选中效果"
    app:indicator_drawable_unselected="指示器未选中效果"
    app:indicator_height="指示器圆形按钮的高度"
    app:indicator_width="指示器圆形按钮的宽度" />
```
#### 2.在Activity或者Fragment中配置Banner 
```java
private Banner banner;
String[] images= new String[] {"url"};
String[] titles=new String[]{"标题"};
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    banner = (Banner) findViewById(R.id.banner);
    banner.setBannerStyle(Banner.CIRCLE_INDICATOR);//设置样式
    banner.setBannerTitle(titles);//设置轮播标题
    banner.setDelayTime(3000);//设置轮播间隔时间
    banner.setImages(images);//可以选择设置图片网址，或者资源文件
    banner.setOnBannerClickListener(this);//设置点击事件

}
```

## 更新说明
#### v1.1.0  
    感谢 <997058003@qq.com> 朋友提的意见，做出了如下更改：
 * 修改指示器样式
 * 增加5种轮播样式，更加灵活方便的运用轮播控件，满足项目需求


