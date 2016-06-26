# Android图片轮播控件 (如果对你有帮助请star哦！) 
<br>
现在的绝大数app都有banner界面，实现循环播放多个广告图片和手动滑动循环等功能。因为ViewPager并不支持循环翻页，
所以要实现循环还得需要自己去动手，所以其他的轮播控件大多都是重写viewpager，而且代码很臃肿。
我就把项目中的控件剔了出来，希望大家觉得有用。
## 效果图
|模式|图片
|---|---|
|指示器模式|![效果示例](https://raw.githubusercontent.com/youth5201314/banner/master/image/1.png)
|数字模式|![效果示例](https://raw.githubusercontent.com/youth5201314/banner/master/image/2.png)
|数字加标题模式|![效果示例](https://raw.githubusercontent.com/youth5201314/banner/master/image/3.png)
|指示器加标题模式|![效果示例](https://raw.githubusercontent.com/youth5201314/banner/master/image/4.png)

### 联系方式
![效果示例](https://raw.githubusercontent.com/youth5201314/banner/master/image/star.png)

* 邮箱地址： 1028729086@qq.com 
* 如果遇到问题和建议欢迎在给我发送邮件，希望让这个工程越来越完善。

<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=1028729086&site=鸡蛋掉了&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:1028729086:51" alt="点击这里给我发消息" title="点击这里给我发消息"/></a>
<a target="_blank" href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=q8fC0t7BwsrFzIXfwOva2oXIxMY" style="text-decoration:none;"><img src="http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_02.png"/></a>

##Gradle
```groovy
dependencies{
    compile 'com.youth.banner:banner:1.1.3'  //指定版本
    compile 'com.youth.banner:banner:+' //最新版本
}
```
或者引用本地lib
```groovy
compile project(':banner')
```
## 常量
|方法名|描述
|---|---|
|Banner.NOT_INDICATOR| 不显示指示器和标题
|Banner.CIRCLE_INDICATOR| 显示圆形指示器
|Banner.NUM_INDICATOR| 显示数字指示器
|Banner.NUM_INDICATOR_TITLE| 显示数字指示器和标题
|Banner.CIRCLE_INDICATOR_TITLE| 显示圆形指示器和标题
|Banner.LEFT| 指示器居左
|Banner.CENTER| 指示器居中
|Banner.RIGHT| 指示器居右
##方法
|方法名|描述
|---|---|
|setBannerStyle(int bannerStyle)| 设置轮播样式（默认为Banner.NOT_INDICATOR）
|setIndicatorGravity(int type)| 设置轮播样式（没有标题默认为右边,有标题时默认左边）
|isAutoPlay(boolean isAutoPlay)| 设置是否自动轮播（默认自动）
|setBannerTitle(String[] titles)| 设置轮播要显示的标题和图片对应（如果不传默认不显示标题）
|setDelayTime(int time)| 设置轮播图片间隔时间（默认为2000）
|setImages(Object[] imagesUrl)| 设置轮播图片(所有设置参数方法都放在此方法之前执行)
|setImages(Object[] imagesUrl,OnLoadImageListener listener)| 设置轮播图片，并且自定义图片加载方式
|setOnBannerClickListener(this)|设置点击事件
|setOnBannerImageListener(this)|设置图片加载事件，可以自定义图片加载方式

##使用步骤

#### 1.在布局文件中添加Banner，可以设置自定义属性

* 简单使用
```xml
<com.youth.banner.Banner
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/banner"
    android:layout_width="match_parent"
    android:layout_height="高度自己设置" />
```
* 深度自定义 
```xml
<com.youth.banner.Banner
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/banner"
    android:layout_width="match_parent"
    android:layout_height="高度自己设置"
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
    /**
     * 需要什么设置，请看着文档在设置图片和标题前完成设置
     */
    //可以选择设置图片网址，或者资源文件，默认加载框架Glide
    //banner.setImages(images);
    //自定义图片加载框架
    banner.setImages(images, new Banner.OnLoadImageListener() {
        @Override
        public void OnLoadImage(ImageView view, Object url) {
            Glide.with(getApplicationContext()).load(url).into(view);
        }
    });
}

//如果你需要考虑更好的体验，可以这么操作
@Override
protected void onStart() {
    super.onStart();
    //在页面可见时开始轮播，
    //默认的是页面初始化时就开始轮播了，如果你不需要可以再onCreate方法里设置banner.isAutoPlay(false);
    banner.isAutoPlay(true);
}

@Override
protected void onStop() {
    super.onStop();
    //在页面不可见时停止轮播
    banner.isAutoPlay(false);
}
```

## 更新说明
#### v1.1.3
    修复了 <2316692710@qq.com> 朋友反馈的bug：
 * bug①  有标题的时候，向左滑动 ,会数组越界崩溃
 * bug②  指示器为数字的时候，向左滑动时会有一次显示为0/5
 
#### v1.1.2
    感谢 <cssxn@qq.com> 朋友提的意见，做出了如下更改：
 * 增加设置轮播图片，并且自定义图片加载方式:setImages(Object[] imagesUrl,OnLoadImageListener listener)
 * 增加设置图片加载事件，可以自定义图片加载方式:setOnBannerImageListener(this)
 
#### v1.1.1
    感谢 <969482412@qq.com> 朋友提的意见，做出了如下更改：
 * 增加圆形指示器的位置方法setIndicatorGravity(int type)
 * 增加设置是否自动轮播的方法isAutoPlay(boolean isAutoPlay)

#### v1.1.0  
    感谢 <997058003@qq.com> 朋友提的意见，做出了如下更改：
 * 修改指示器样式
 * 增加5种轮播样式，更加灵活方便的运用轮播控件，满足项目需求



