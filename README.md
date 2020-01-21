## Banner 2.0 
[![Apache 2.0 License](https://img.shields.io/badge/license-Apache%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0.html)
> 只做一个可以自定义的轮播容器，不侵入UI ———— Banner 2.0

### 阔别已久，从新回归
> 很长一段时间没有维护这个项目了，有很多人发邮件反馈，有好的，也有让人无语气氛的；特别是2017经常收到各种骂人的邮件，所以我只能停止更新关闭邮件提醒。
但是隔了这么长时间看突然发现有这么多人使用，又反馈了很多问题和意见，我决定重新开始重构下，能更好的解决大家的需求：

- 首先我声明几点：
    * 这只是开源交流的平台，如果满意你可以使用、可以借鉴修改，希望对你们有所帮助。
    * 如果不满意请友好的提出，注明详细信息或者修改建议，亦可以直接提交，我会考虑合并。
    * 如果你觉得实在是没用，也请你做一个有自我修养的人。
   
### 主要改进功能介绍
- [x] 使用了ViewPager2为基础控件  <a href="https://developer.android.google.cn/jetpack/androidx/releases/viewpager2" target="_blank">[ViewPager2介绍]
- [x] 支持了androidx兼容包
- [x] 方便了UI、Indicator自定义（现在还是基础版本）
- [x] 依赖包目前只导入了ViewPager2

### 已知问题

`在转到稳定版之前，我仍在努力解决剩余待解决的问题，也希望大家反馈新版的bug，其他功能会慢慢叠加先保证基础功能稳定。`


### 联系方式  <a target="_blank" href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=KBkYGhAfGhEYEB5oWVkGS0dF" style="text-decoration:none;"><img src="http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_11.png"/></a>
 ![效果示例](http://oceh51kku.bkt.clouddn.com/Android%E6%8A%80%E6%9C%AF%E4%BA%A4%E6%B5%81%E7%BE%A4%E4%BA%8C%E7%BB%B4%E7%A0%81.png)
* 如果有问题可以加群大家一起交流
* 我的个人微博：https://weibo.com/u/3013494003 有兴趣的也可以关注，大家一起交流


### 效果图

后面补上


## 方法
|方法名|返回类型|描述|
|---|---|---|
|getAdapter()|extends BannerAdapter|获取你设置的BannerAdapter
|getViewPager2()|ViewPager2|获取ViewPager2
|getIndicator()|Indicator|获取你设置的指示器（没有设置直接获取会抛异常哦）
|getIndicatorConfig()|IndicatorConfig|获取你设置的指示器配置信息（没有设置直接获取会抛异常哦）
|getRealCount()|int|返回banner真实总数
|setUserInputEnabled(boolean)|this|禁止手动滑动Banner;true 允许，false 禁止
|setDatas(List<T>)|this|重新设置banner数据
|isAutoLoop(boolean)|this|是否允许自动轮播
|setDelayTime(long)|this|设置轮播间隔时间（毫秒）
|start()|this|开始轮播(主要配合生命周期使用)，或者你手动暂停再次启动
|stop()|this|停止轮播(主要配合生命周期使用)，或者你需要手动暂停
|setAdapter(T extends BannerAdapter)|this|设置banner的适配器
|setOrientation(@Orientation)|this|设置banner轮播方向(垂直or水平)
|setOnBannerListener(this)|this|设置点击事件，下标是从0开始
|addOnPageChangeListener(this)|this|设置viewpager的滑动监听
|setPageTransformer|this|设置viewpager的切换效果
|setIndicator(Indicator)|this|设置banner轮播指示器(提供有base和接口，可以自定义)
|setIndicatorSelectedColor(@ColorInt)|this|设置指示器选中颜色
|setIndicatorSelectedColorRes(@ColorRes)|this|设置指示器选中颜色
|setIndicatorNormalColor(@ColorInt)|this|设置指示器默认颜色
|setIndicatorNormalColorRes(@ColorRes)|this|设置指示器默认颜色
|setIndicatorGravity(@IndicatorConfig.Direction)|this|设置指示器位置（左，中，右）
|setIndicatorSpace(float)|this|设置指示器之间的间距
|setIndicatorMargins(IndicatorConfig.Margins)|this|设置指示器的Margins
|setIndicatorWidth(int,int)|this|设置指示器选中和未选中的宽度，直接影响绘制指示器的大小

## Attributes属性
>在banner布局文件中调用,如果你自定义了indicator请做好兼容处理

|Attributes|forma|describe
|---|---|---|
|delay_time|integer|轮播间隔时间，默认3000
|is_auto_loop|boolean|是否自动轮播，默认true
|orientation|enum|轮播方向：horizontal（默认） or vertical
|indicator_normal_width|dimension|指示器默认的宽度，默认6dp
|indicator_selected_width|dimension|指示器选中的宽度，默认8dp
|indicator_normal_color|color|指示器默认颜色，默认0x88ffffff
|indicator_selected_color|color|指示器选中颜色，默认0x88000000
|indicator_space|dimension|指示器之间的间距，默认6dp
|indicator_gravity|dimension|指示器位置，默认center
|indicator_margin|dimension|指示器的margin,默认5dp，不能和下面的同时使用
|indicator_marginLeft|dimension|指示器左边的margin
|indicator_marginTop|dimension|指示器上边的margin
|indicator_marginRight|dimension|指示器右边的margin
|indicator_marginBottom|dimension|指示器下边的margin


## 使用步骤
>以下提供的是最简单的步骤，需要复杂的样式自己可以自定义

#### Step 1.依赖banner
Gradle 
```groovy
dependencies{
    compile 'com.youth.banner:banner:2.0.0-alpha'  //预览版
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

```

#### Step 3.在布局文件中添加Banner，可以设置自定义属性
！！！此步骤可以省略，可以直接在Activity或者Fragment中new Banner();
```xml
<com.youth.banner.Banner
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/banner"
    android:layout_width="match_parent"
    android:layout_height="高度自己设置" />
```

#### Step 4.继承BannerAdapter，和RecyclerView的Adapter一样
```java

/**
 * 自定义布局，随你发挥
 */
public class BannerExampleAdapter extends BannerAdapter<DataBean, BannerExampleAdapter.PagerViewHolder> {

    public BannerExampleAdapter(List<DataBean> mDatas) {
        //设置数据，也可以调用banner提供的方法
        super(mDatas);
    }

    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public PagerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        //注意布局文件，item布局文件要设置为match_parent，这个是viewpager2强制要求的
        //或者调用BannerUtils.getView(parent,R.layout.banner);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner, parent, false);
        return new PagerViewHolder(view);
    }

    //绑定数据
    @Override
    public void onBindView(PagerViewHolder holder, DataBean data, int position, int size) {
        holder.imageView.setImageResource(data.imageRes);
        holder.title.setText(data.title);
        //可以在布局文件中自己实现指示器，亦可以使用banner提供的方法自定义指示器，目前样式较少，后面补充
        holder.numIndicator.setText((position + 1) + "/" + size);
    }


    class PagerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        TextView numIndicator;

        public PagerViewHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.image);
            title = view.findViewById(R.id.bannerTitle);
            numIndicator = view.findViewById(R.id.numIndicator);
        }
    }

}

```

#### Step 5.Banner具体方法调用 

```java
public class BannerActivity extends AppCompatActivity {
    public void useBanner() {
        //--------------------------简单使用-------------------------------
        //创建（new banner()）或者布局文件中获取banner
        Banner banner = (Banner) findViewById(R.id.banner);
        //默认直接设置adapter就行了
        banner.setAdapter(new BannerExampleAdapter(DataBean.getTestData()));
        
        //--------------------------详细使用-------------------------------
        banner.setAdapter(new BannerExampleAdapter(DataBean.getTestData()));
        banner.setIndicator(new CircleIndicator(this));
        banner.setIndicatorSelectedColorRes(R.color.main_color);
        banner.setIndicatorNormalColorRes(R.color.textColor);
        banner.setIndicatorGravity(IndicatorConfig.Direction.LEFT);
        banner.setIndicatorSpace(BannerUtils.dp2px(20));
        banner.setIndicatorMargins(new IndicatorConfig.Margins((int) BannerUtils.dp2px(10)));
        banner.setIndicatorWidth(10,20);
        banner.addItemDecoration(new MarginItemDecoration((int) BannerUtils.dp2px(50)));
        banner.setPageTransformer(new DepthPageTransformer());
        banner.setOnBannerListener(this);
        banner.addOnPageChangeListener(this);
        
        //-----------------当然如果你想偷下懒也可以这么用--------------------
        //banner所有set方法都支持链式调用(以下列举了一些方法)
        banner.setAdapter(new BannerExampleAdapter(DataBean.getTestData()))
                .setOrientation(Banner.VERTICAL)
                .setIndicator(new CircleIndicator(this))
                .setUserInputEnabled(false);
    }
}
```

#### Step 6.（可选）生命周期改变优化体验
```java
public class BannerActivity extends AppCompatActivity {
    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        banner.start();
    }
    
    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        banner.stop();
    }
}
```




## 常见问题

* 等使用一段时间在整理

    
## Thanks

- []()

## 更新说明

#### v2.0.0-alpha
  





