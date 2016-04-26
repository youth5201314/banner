#Android图片轮播控件  
<br>
现在的绝大数app都有banner界面，实现循环播放多个广告图片和手动滑动循环等功能。因为ViewPager并不支持循环翻页，
所以要实现循环还得需要自己去动手，所以其他的轮播控件大多都是重写viewpager，而且代码很臃肿。
我就把项目中的控件剔了出来，希望大家觉得有用。

##Gradle
```groovy
dependencies{
    compile 'com.youth.banner:library:1.0'
}
```
##方法详解
|方法名|参数|描述
|:---:|:---:|:---:|
|setImages|url/resources| 设置轮播图片 
