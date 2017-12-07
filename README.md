# ViewMonitor

提供曝光回调功能，用于曝光埋点

# 功能特点
- 从在视图中无法看到变成可以在视图中观察到，算一次曝光
- 支持ScrollView、ViewPager、RecyclerView、ListView
- 支持Fragment切换、Activity切换、前后台切换
- 快速滑动时，惯性滑过的View也全部曝光

# 设计原则
- 尽可能和业务脱离

# 使用方法
```java
// 初始化
ExposureManager.getInstance().init(this);

// 监听曝光的View需要使用ExposureView替换
<com.garfield.viewmonitor.api.view.ExposureFrameLayout
   ....
</com.garfield.viewmonitor.api.view.ExposureFrameLayout>

// 使用ScrollView
如果是ScrollView，请使用ExposureScrollView代替

// 绑定数据
【ListView、RecycleView】
mExposureView.bindListData(data, position, callback);
【ScrollView、ViewPager】
mExposureView.bindData(data, position, null, callback);

// 曝光回调
方法1. 可以在bind数据时，传入callback
方法2. 也可以覆写ExposureFrameLayout的onExposure方法

```

# Demo
- 运行后，捕获关键字 "view_monitor"

# 作者
- 微信：122525660
- 微书：gwblue
- 邮箱：gwblue@163.com

# License
Apache
