# Android图片轮播控件
[![Apache 2.0 License](https://img.shields.io/badge/license-Apache%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0.html)


## 新框架发布，欢迎大家Star

[XFrame - Android快速开发框架](https://github.com/youth5201314/XFrame)

[XFrame详细功能文档预览](https://github.com/youth5201314/XFrame/wiki)


<br>

现在的绝大数app都有banner界面，实现循环播放多个广告图片和手动滑动循环等功能。因为ViewPager并不支持循环翻页，
所以要实现循环还得需要自己去动手，我就把项目中的控件剔了出来，希望大家觉得有用。目前框架可以进行不同样式、不同动画设置，
以及完善的api方法能满足大部分的需求了。

## 效果图

|模式|图片
|---|---|
|指示器模式|![效果示例](http://oceh51kku.bkt.clouddn.com/banner_example1.png)|
|数字模式|![效果示例](http://oceh51kku.bkt.clouddn.com/banner_example2.png)|
|数字加标题模式|![效果示例](http://oceh51kku.bkt.clouddn.com/banner_example3.png)|
|指示器加标题模式<br>垂直显示|![效果示例](http://oceh51kku.bkt.clouddn.com/banner_example4.png)|
|指示器加标题模式<br>水平显示|![效果示例](http://oceh51kku.bkt.clouddn.com/banner_example5.png)|

### 联系方式  <a target="_blank" href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=KBkYGhAfGhEYEB5oWVkGS0dF" style="text-decoration:none;"><img src="http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_11.png"/></a>
 ![效果示例](http://oceh51kku.bkt.clouddn.com/Android%E6%8A%80%E6%9C%AF%E4%BA%A4%E6%B5%81%E7%BE%A4%E4%BA%8C%E7%BB%B4%E7%A0%81.png)
* 如果有问题可以加群大家一起交流
* 我的个人微博：https://weibo.com/u/3013494003 有兴趣的也可以关注，大家一起交流

## 常量
|常量名称|描述|所属方法
|---|---|---|
|BannerConfig.NOT_INDICATOR| 不显示指示器和标题|setBannerStyle
|BannerConfig.CIRCLE_INDICATOR| 显示圆形指示器|setBannerStyle
|BannerConfig.NUM_INDICATOR| 显示数字指示器|setBannerStyle
|BannerConfig.NUM_INDICATOR_TITLE| 显示数字指示器和标题|setBannerStyle
|BannerConfig.CIRCLE_INDICATOR_TITLE| 显示圆形指示器和标题（垂直显示）|setBannerStyle
|BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE| 显示圆形指示器和标题（水平显示）|setBannerStyle
|BannerConfig.LEFT| 指示器居左|setIndicatorGravity
|BannerConfig.CENTER| 指示器居中|setIndicatorGravity
|BannerConfig.RIGHT| 指示器居右|setIndicatorGravity

## 动画常量类（setBannerAnimation方法调用）
[ViewPagerTransforms](https://github.com/ToxicBakery/ViewPagerTransforms) `动画时集成的第三方库，可能有兼容问题导致position位置不准确，你可以选择参考动画然后自定义动画`

|常量类名|
|---|
|Transformer.Default| 
|Transformer.Accordion| 
|Transformer.BackgroundToForeground| 
|Transformer.ForegroundToBackground| 
|Transformer.CubeIn| 
|Transformer.CubeOut| 
|Transformer.DepthPage| 
|Transformer.FlipHorizontal| 
|Transformer.FlipVertical| 
|Transformer.RotateDown| 
|Transformer.RotateUp| 
|Transformer.ScaleInOut| 
|Transformer.Stack| 
|Transformer.Tablet| 
|Transformer.ZoomIn| 
|Transformer.ZoomOut| 
|Transformer.ZoomOutSlide| 


## 方法
|方法名|描述|版本限制
|---|---|---|
|setBannerStyle(int bannerStyle)| 设置轮播样式（默认为CIRCLE_INDICATOR）|无
|setIndicatorGravity(int type)| 设置指示器位置（没有标题默认为右边,有标题时默认左边）|无
|isAutoPlay(boolean isAutoPlay)| 设置是否自动轮播（默认自动）|无
|setViewPagerIsScroll(boolean isScroll)| 设置是否允许手动滑动轮播图（默认true）|1.4.5开始
|update(List<?> imageUrls,List<String> titles)| 更新图片和标题 |1.4.5开始
|update(List<?> imageUrls)| 更新图片 |1.4.5开始
|startAutoPlay()|开始轮播|1.4开始，此方法只作用于banner加载完毕-->需要在start()后执行
|stopAutoPlay()|结束轮播|1.4开始，此方法只作用于banner加载完毕-->需要在start()后执行
|start()|开始进行banner渲染（必须放到最后执行）|1.4开始
|setOffscreenPageLimit(int limit)|同viewpager的方法作用一样|1.4.2开始
|setBannerTitle(String[] titles)| 设置轮播要显示的标题和图片对应（如果不传默认不显示标题）|1.3.3结束
|setBannerTitleList(List<String> titles)| 设置轮播要显示的标题和图片对应（如果不传默认不显示标题）|1.3.3结束
|setBannerTitles(List<String> titles)| 设置轮播要显示的标题和图片对应（如果不传默认不显示标题）|1.4开始
|setDelayTime(int time)| 设置轮播图片间隔时间（单位毫秒，默认为2000）|无
|setImages(Object[]/List<?> imagesUrl)| 设置轮播图片(所有设置参数方法都放在此方法之前执行)|1.4后去掉数组传参
|setImages(Object[]/List<?> imagesUrl,OnLoadImageListener listener)| 设置轮播图片，并且自定义图片加载方式|1.3.3结束
|setOnBannerClickListener(this)|设置点击事件，下标是从1开始|无（1.4.9以后废弃了）
|setOnBannerListener(this)|设置点击事件，下标是从0开始|1.4.9以后
|setOnLoadImageListener(this)|设置图片加载事件，可以自定义图片加载方式|1.3.3结束
|setImageLoader(Object implements ImageLoader)|设置图片加载器|1.4开始
|setOnPageChangeListener(this)|设置viewpager的滑动监听|无
|setBannerAnimation(Class<? extends PageTransformer> transformer)|设置viewpager的默认动画,传值见动画表|无
|setPageTransformer(boolean reverseDrawingOrder, ViewPager.PageTransformer transformer)|设置viewpager的自定义动画|无

## Attributes属性（banner布局文件中调用）
|Attributes|forma|describe
|---|---|---|
|delay_time| integer|轮播间隔时间，默认2000
|scroll_time| integer|轮播滑动执行时间，默认800
|is_auto_play| boolean|是否自动轮播，默认true
|title_background| color|reference|标题栏的背景色
|title_textcolor| color|标题字体颜色
|title_textsize| dimension|标题字体大小
|title_height| dimension|标题栏高度
|indicator_width| dimension|指示器圆形按钮的宽度
|indicator_height| dimension|指示器圆形按钮的高度
|indicator_margin| dimension|指示器之间的间距
|indicator_drawable_selected| reference|指示器选中效果
|indicator_drawable_unselected| reference|指示器未选中效果
|image_scale_type| enum |和imageview的ScaleType作用一样
|banner_default_image| reference | 当banner数据为空是显示的默认图片
|banner_layout| reference |自定义banner布局文件，但是必须保证id的名称一样（你可以将banner的布局文件复制出来进行修改）


### <a href="http://youth5201314.github.io/2016/08/24/ViewPager%E5%88%87%E6%8D%A2%E5%8A%A8%E7%94%BBPageTransformer%E4%BD%BF%E7%94%A8/" target="_blank"> [ 点击查看 ViewPager的PageTransformer用法 ]


## 使用步骤

#### Step 1.依赖banner
Gradle 
```groovy
dependencies{
    compile 'com.youth.banner:banner:1.4.10'  //最新版本
}
```
或者引用本地lib
```groovy
compile project(':banner')
```


#### Step 2.添加权限到你的 AndroidManifest.xml
```xml
<!-- if you want to load images from the internet -->
<uses-permission android:name="android.permission.INTERNET" /> 

<!-- if you want to load images from a file OR from the internet -->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```

#### Step 3.在布局文件中添加Banner，可以设置自定义属性
！！！此步骤可以省略，直接在Activity或者Fragment中new Banner();
```xml
<com.youth.banner.Banner
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/banner"
    android:layout_width="match_parent"
    android:layout_height="高度自己设置" />
```

#### Step 4.重写图片加载器
```java
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        /**
          注意：
          1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
          2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
          传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
          切记不要胡乱强转！
         */
        eg：
        
        //Glide 加载图片简单用法
        Glide.with(context).load(path).into(imageView);

        //Picasso 加载图片简单用法
        Picasso.with(context).load(path).into(imageView);
        
        //用fresco加载图片简单用法，记得要写下面的createImageView方法
        Uri uri = Uri.parse((String) path);
        imageView.setImageURI(uri);
    }
    
    //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
    @Override
    public ImageView createImageView(Context context) {
        //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
        SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
        return simpleDraweeView;
    }
}
```

#### Step 5.在Activity或者Fragment中配置Banner 

- 注意！start()方法必须放到最后执行，点击事件请放到start()前，每次都提交问题问为什么点击没有反应？需要轮播一圈才能点击？点击第一个怎么返回1？麻烦仔细阅读文档。

```java
--------------------------简单使用-------------------------------
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Banner banner = (Banner) findViewById(R.id.banner);
    //设置图片加载器
    banner.setImageLoader(new GlideImageLoader());
    //设置图片集合
    banner.setImages(images);
    //banner设置方法全部调用完毕时最后调用
    banner.start();
}
--------------------------详细使用-------------------------------
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Banner banner = (Banner) findViewById(R.id.banner);
    //设置banner样式
    banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
    //设置图片加载器
    banner.setImageLoader(new GlideImageLoader());
    //设置图片集合
    banner.setImages(images);
    //设置banner动画效果
    banner.setBannerAnimation(Transformer.DepthPage);
    //设置标题集合（当banner样式有显示title时）
    banner.setBannerTitles(titles);
    //设置自动轮播，默认为true
    banner.isAutoPlay(true);
    //设置轮播时间
    banner.setDelayTime(1500);
    //设置指示器位置（当banner模式中有指示器时）
    banner.setIndicatorGravity(BannerConfig.CENTER);
    //banner设置方法全部调用完毕时最后调用
    banner.start();
}
-----------------当然如果你想偷下懒也可以这么用--------------------
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Banner banner = (Banner) findViewById(R.id.banner);
    banner.setImages(images).setImageLoader(new GlideImageLoader()).start();
}
```

#### Step 6.（可选）增加体验
```java
//如果你需要考虑更好的体验，可以这么操作
@Override
protected void onStart() {
    super.onStart();
    //开始轮播
    banner.startAutoPlay();
}

@Override
protected void onStop() {
    super.onStop();
    //结束轮播
    banner.stopAutoPlay();
}
```

## 混淆代码
```java
# glide 的混淆代码
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
# banner 的混淆代码
-keep class com.youth.banner.** {
    *;
 }

```

<a href="https://dl.bintray.com/youth5201314/maven/com/youth/banner/banner/" target="_blank"> [历史版本资源地址]

<a href="http://youth5201314.github.io/2016/04/13/Banner%E5%BC%80%E6%BA%90%E6%A1%86%E6%9E%B6-Android%E8%BD%AE%E6%92%AD%E6%8E%A7%E4%BB%B6/" target="_blank"> [1.3.3以前旧版本使用文档地址]

## 常见问题

* 问：eclipse怎么使用banner？

    * 答：`在历史版本列表中下载你想要版本的aar包提取最新资源/也可以自己把工程转成eclipse的` <br>
          eclipse的集成demo群文件里有共享！

* 问：怎么显示的一片空白？
    * 答：<br>
        1、没有添加网络权限<br>
        2、检查图片链接是否能打开。
* 问：怎么加载其他图片资源（资源文件、文件、Uri、assets、raw、ContentProvider、sd卡资源）？
    * 答：列如！如果你使用的是glide，那么可以如下操作，其他图片图片加载框架可能有不同
        ```java 
        //资源文件
        Integer[] images={R.mipmap.a,R.mipmap.b,R.mipmap.c};
        //Uri
        Uri uri = resourceIdToUri(context, R.mipmap.ic_launcher);
        Uri[] images={uri};
        //文件对象
        File[] images={"文件对象","文件对象"};
        //raw 两种方式
        String[] images={"Android.resource://com.frank.glide/raw/raw_1"};
        String[] images={"android.resource://com.frank.glide/raw/"+R.raw.raw_1"};
        //ContentProvider
        String[] images={"content://media/external/images/media/139469"};
        //assets
        String[] images={"file:///android_asset/f003.gif"};
        //sd卡资源
        String[] images={"file://"+ Environment.getExternalStorageDirectory().getPath()+"/test.jpg"};
        
        banner.setImages(images);//这里接收集合,上面写成集合太占地方，这个大家举一反三就行了啊
        ```
        
* 问：设置banner指示器颜色怎么变成方的了？

    * 答：首先我先要说很多软件的指示器也是矩形的，然后banner的指示器可以设置color、资源图片、drawable文件夹自定义shape ，
    所以形状你自己可以根据需求定义哦！

* 问：为什么banner的点击事件没有反应，需要下一次轮播才行？点击第一个图片怎么返回1？

     * 答：请将点击事件放在start方法之前执行，start必须放到最后执行，详情可以看demo。

## Thanks

- [ViewPagerTransforms](https://github.com/ToxicBakery/ViewPagerTransforms)

## 更新说明

#### v1.4.10
    很久没有维护banner了，有工作原因比较忙，也有经常遇见一些素质低的人，感觉整个世界都欠他们的，特别影响心情。就放弃更新维护了，
    但是这半年每天邮箱都会收到各种建议反馈，也有很多人私信我，所以在此修复一些当前版本bug，
    关于有朋友要求让轮播类型可以自定义，不局限于imageview的需求，
    这个过段时间再发布一个全新的banner版本，会更加灵活，就不在原来的上面弄了，到时候分两个版本走！

 * 解决轮播手动滑动跳转问题：从第一张-->最后一张-->直接跳转到第二张
 * 解决update刷新轮播图崩溃问题
 * 将onPageScrolled和onPageSelected方法返回的position转成真实的position
 * 增加属性banner_default_image，设置当banner数据为空是显示的默认图片
 * 增加属性banner_layout，可以自定义布局文件，但是必须保证id的名称一样
 * 修改ViewPager偶发性的越界问题
 * SwipeRefreshLayout嵌套ViewPager的滑动冲突问题参考demo的SuperSwipeRefreshLayout类

#### v1.4.9
    banner 优化更新
 * 废弃以前的点击事件(当然还是可以使用以前的方法)，增加新的setOnBannerListener点击事件，下标从0开始
 * 解决update刷新轮播图后，会造成多次调用OnPageChangeListener的情况
 * 改变布局文件变量名，减少和工程冲突

#### v1.4.8
    banner 优化更新
 * 修改点击事件返回下标偶尔越界问题

#### v1.4.7
    banner 优化更新
 * 修复从第一个到最后一个，和从最后一个到第一个，数字和标题切换有点延迟的问题

#### v1.4.6
    banner 优化更新
 * 修改demo，更容易理解
 * 修复第一张过渡第二张图片切换时间翻倍问题
 * 图片默认全屏展示

#### v1.4.5
    banner 优化更新
 * 增加setViewPagerIsScroll(boolean isScroll)方法控制是否允许手动滑动轮播图，默认为true
 * 增加update()方法，方便更新图片
 * 解决最后一张图片切换到第一张，会出现卡顿(特别是不设置动画时有点明显)
 
#### v1.4.3-1.4.4
    banner bug修改
 * 轮播图变少时刷新崩溃问题
 * 增加控制图片显示属性 image_scale_type 的属性值（center，center_crop，center_inside，fit_center，fit_end，fit_start，fit_xy，matrix），和 ImageView 的效果一样
 * 当只有一张图片时不显示圆形指示器和数字指示器   
 
#### v1.4.2
    banner优化更新<感谢 694551594，FeverCombo3,MIkeeJY >
 * ！！！注意！！ImageLoader已从接口改变成抽象类，请调整下代码哦！
 * ImageLoader中增加ImageView控件创建方法createImageView()，可以满足fresco加载图片时扩展ImageView需求 
 * 修改关于banner刷新时需要第二轮才会更新图片问题(同title更新图片不更新问题)，具体看demo
 * 开放viewpager的setOffscreenPageLimit(int limit)方法
 * 优化banner在开始0s~20s之间会出现的内存泄漏问题
 * 优化最后一张到第一张之间滑动卡顿现象

#### v1.4.1
    bug修改<感谢深圳-放飞，台北-Tom>
 * 第一次加载一张图片(不能滑动[正常])-->刷新-->第二次加载多张图片(不能滑动[bug])
 * 滑动事件传递拦截优化
 * demo里添加了下拉刷新和RecyclerView添加头部的两种方式

#### v1.4
    全新升级，此次更新比较大，如果不习惯使用1.4的还是可以用1.3.3
 * 去掉app:default_image="默认加载图片"，需要可以自己在图片加载器中设置
 * 去掉glide图片加载相关代码全部用户自定义，外部通过实现（ImageLoader）去加载图片，尽力减少对第三方库的依赖
 * 去掉OnLoadImageListener图片加载监听事件，增加ImageLoader接口，通过setImageLoader设置图片加载器
 * 去掉isAutoPlay方法，改用startAutoPlay()|stopAutoPlay()这两个方法只能是渲染完（start()）后调用
 * 调整代码结构和执行顺序，由start()进行最后渲染和逻辑判断，前面方法可以随意调用打乱顺序。
 * 将设置图片和标题的方法改成setImages和setBannerTitles，传参方式改成集合，如果要用数组可以Arrays.asList()转成集合使用
 * 调整默认样式为CIRCLE_INDICATOR
 * 禁止单张轮播手动滑动问题
 * banner的标题文字单位指定为TypedValue.COMPLEX_UNIT_PX
 * demo改版，如果需要1.3.3的demo请在QQ群中下载
 

#### v1.3.3
    优化轮播首尾过渡时间
 * 再实现轮播从最后一张到第一张时，在第一张前面加了一张图片用于过渡，保证轮播不太生硬。这样也就造成了第一张时间有点长，
    开始没有发现，感谢大家的反馈，现在简单优化了下依然保留第一张500毫秒的时间用于过渡，让轮播保证流畅性。相信500毫秒的时间
    对于效果没有什么影响，这段时间很忙后面会对算法进行修改，目前先这样用着吧。

#### v1.3.2
    修复bug
 * 解决在自动轮播中，轮播中途触摸图片/左右移动时停止轮播，抬起不自动轮播问题
 
#### v1.3.1
    修复bug
 * app:delay_time="轮播间隔时间" 参数无用问题
 * 在暂停轮播时，当你手动滑动时会重新开始轮播问题
 * 在轮播中，当你按住轮播时暂停，松开后不会轮播问题
 
#### v1.2.9
    修复bug以及更新功能
 * app:image_scale_type="fit_xy,和imageview的ScaleType作用一样，不过只提供了两个常用的"
 * 修复设置动画后点击事件失效的问题。
 * 取消setScrollerTime设置方法

#### v1.2.8
    增加ViewPager的切换速度设置方法，以及动画的重新封装
 * 整理了17种viewpager过渡动画，并整理为常量方便调用，改变了传参格式，如果不够用可以自行自定义动画
 * 增加setScrollerTime(int duration)设置viewpager的切换速度,（单位毫秒，默认800）
 
#### v1.2.7
    增加viewpager的切换默认几种动画，和自定义动画方法
 * setBannerAnimation(int type)设置viewpager的默认动画
 * setPageTransformer(boolean reverseDrawingOrder, ViewPager.PageTransformer transformer)设置viewpager的自定义动画

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



