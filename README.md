# Android图片轮播控件 *如果对你有帮助请star哦！*
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
|指示器加标题模式<br>垂直显示|![效果示例](https://raw.githubusercontent.com/youth5201314/banner/master/image/4.png)
|指示器加标题模式<br>水平显示|![效果示例](https://raw.githubusercontent.com/youth5201314/banner/master/image/5.png)

### 联系方式  <a target="_blank" href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=KBkYGhAfGhEYEB5oWVkGS0dF" style="text-decoration:none;"><img src="http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_11.png"/></a>
![效果示例](https://raw.githubusercontent.com/youth5201314/banner/master/image/Android技术交流群二维码.png)
* 如果遇到问题和建议欢迎在给我发送邮件或者加入qq群，希望让这个工程越来越完善。


##Gradle
```groovy
dependencies{
    compile 'com.youth.banner:banner:1.2.5'  //指定版本
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
|BannerConfig.NOT_INDICATOR| 不显示指示器和标题
|BannerConfig.CIRCLE_INDICATOR| 显示圆形指示器
|BannerConfig.NUM_INDICATOR| 显示数字指示器
|BannerConfig.NUM_INDICATOR_TITLE| 显示数字指示器和标题
|BannerConfig.CIRCLE_INDICATOR_TITLE| 显示圆形指示器和标题（垂直显示）
|BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE| 显示圆形指示器和标题（水平显示）
|BannerConfig.LEFT| 指示器居左
|BannerConfig.CENTER| 指示器居中
|BannerConfig.RIGHT| 指示器居右
##方法
|方法名|描述
|---|---|
|setBannerStyle(int bannerStyle)| 设置轮播样式（默认为Banner.NOT_INDICATOR）
|setIndicatorGravity(int type)| 设置指示器位置（没有标题默认为右边,有标题时默认左边）
|isAutoPlay(boolean isAutoPlay)| 设置是否自动轮播（默认自动）
|setBannerTitle(String[] titles)| 设置轮播要显示的标题和图片对应（如果不传默认不显示标题）
|setBannerTitleList(List<String> titles)| 设置轮播要显示的标题和图片对应（如果不传默认不显示标题）
|setDelayTime(int time)| 设置轮播图片间隔时间（默认为2000）
|setImages(Object[]/List<?> imagesUrl)| 设置轮播图片(所有设置参数方法都放在此方法之前执行)
|setImages(Object[]/List<?> imagesUrl,OnLoadImageListener listener)| 设置轮播图片，并且自定义图片加载方式
|setOnBannerClickListener(this)|设置点击事件，下标是从1开始
|setOnBannerImageListener(this)|设置图片加载事件，可以自定义图片加载方式
|setOnPageChangeListener(this)|viewpager的滑动监听

##使用步骤 \* 更多用法请下载demo

#### 1.在布局文件中添加Banner，可以设置自定义属性
* 简单使用
```xml
<com.youth.banner.Banner
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/banner"
    android:layout_width="match_parent"
    android:layout_height="高度自己设置" />
```
* 深度自定义,xml扩展属性
* !!!! 有些属性和方法有重复的地方，完全是为了考虑不同人的习惯
```xml
<com.youth.banner.Banner
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/banner"
    android:layout_width="match_parent"
    android:layout_height="高度自己设置"
    app:default_image="默认加载图片"
    app:delay_time="轮播间隔时间"
    app:is_auto_play="是否自动轮播"
    app:indicator_margin="指示器之间的间距"
    app:indicator_drawable_selected="指示器选中效果"
    app:indicator_drawable_unselected="指示器未选中效果"
    app:indicator_height="指示器圆形按钮的高度"
    app:indicator_width="指示器圆形按钮的宽度"
    app:title_background="标题栏的背景色"
    app:title_height="标题栏高度"
    app:title_textcolor="标题字体颜色"
    app:title_textsize="标题字体大小"/>
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
    //一步搞定，设置图片就行了
    banner.setImages(images);
 
}

```

## 更新说明

#### v1.2.5
    修改bug
 * app:title_height="标题栏高度"，高度过小文字不显示问题
#### v1.2.4
    优化更新
 * app:title_background="标题栏的背景色"
 * app:title_height="标题栏高度"
 * app:title_textcolor="标题字体颜色"
 * app:title_textsize="标题字体大小"  
    
#### v1.2.3
    优化更新
 * 修复刷新banner从多张到1张时，还出现滑动的问题
 * demo增加功能
    
#### v1.2.2
    优化更新
 * 增加BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE显示圆形指示器和标题（水平显示）
 * 修改数字指示器时，变形问题。
 
#### v1.2.1
    优化更新
 * 修复NUM_INDICATOR和NUM_INDICATOR_TITLE模式下，没有轮播初始化为“1/1”的情况
 * 将图片加载默认图片取消，开发者可根据需要设置

#### v1.2.0
    优化更新
 * 修复小图片每次轮播被放大的问题
 * 开放了viewpager的滑动事件setOnPageChangeListener()
 * 增加触摸轮播图时暂停轮播，离开时继续轮播
 * 增加demo代码解释，关于刷新下标越界问题这个不存在，不懂的请看demo，
    不要一出问题就认为是banner的错，看看自己的用法是不是出来问题。
 
#### v1.1.9
    优化更新
 * 当图片为一张时，禁止轮播
 * 优化标题初始化速度
 
#### v1.1.8
    bug修改
 * 可能的存在的图片拉伸问题，替换为glide的图片加载大小计算
 * 修改关于非arraylist集合的强转问题。
 
#### v1.1.7
    应<wuzhaohui026>朋友的要求，做出更新
 * 为标题增加设置集合的方法：setBannerTitleList(List<String> titles)
 
#### v1.1.6
    综合大家的反馈，做出一些更新
 * 将代码的常量全部提出到BannerConfig类里了，以前的代码，大家需要修改下
 * 有人喜欢xml属性的方式来设置参数，那么增加了几个xml属性
     app:default_image="默认加载图片"
     app:delay_time="轮播间隔时间"
     app:is_auto_play="是否自动轮播"
 * 增加了设置glide加载方式的默认加载图片方法
     app:default_image="默认加载图片"
 * 重新写了一下demo，方便大家更加容易懂

#### v1.1.5
    感谢<imexception>朋友的反馈
 * 创建指示器初始化时默认的背景的添加，减少延迟等待更新
 * 优化指示器背景更新操作

#### v1.1.4
    更新内容
 * 增加setImages传参可以接收list集合
 * 优化在添加数据和创建指示器时的对象内存回收
 
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



