# Android图片轮播控件

<br>

在原项目的基础上加入不循环播放的功能，具体使用步骤可以参照Base项目

## 效果图

|模式|图片
|---|---|
|指示器模式|![效果示例](http://oceh51kku.bkt.clouddn.com/banner_example1.png)|
|数字模式|![效果示例](http://oceh51kku.bkt.clouddn.com/banner_example2.png)|
|数字加标题模式|![效果示例](http://oceh51kku.bkt.clouddn.com/banner_example3.png)|
|指示器加标题模式<br>垂直显示|![效果示例](http://oceh51kku.bkt.clouddn.com/banner_example4.png)|
|指示器加标题模式<br>水平显示|![效果示例](http://oceh51kku.bkt.clouddn.com/banner_example5.png)|

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



