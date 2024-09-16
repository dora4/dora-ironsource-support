dora-ironsource-support
![Release](https://jitpack.io/v/dora4/dora-ironsource-support.svg)
--------------------------------

#### Gradle依赖配置

添加以下代码到项目根目录下的settings.gradle.kts
```kotlin
dependencyResolutionManagement {
    repositories {
        maven { setUrl("https://jitpack.io") }
        // IronSource
        maven { setUrl("https://android-sdk.is.com/") }
    }
}
```

添加以下代码到app模块的build.gradle.kts
```kotlin
dependencies {
    // 扩展包必须在有主框架dora的情况下使用
    implementation("com.github.dora4:dora:1.2.26")
    implementation("com.github.dora4:dora-ironsource-support:1.0")
}
```

#### 使用方式

Application中加入代码。
```kotlin
override fun attachBaseContext(base: Context?) {
    super.attachBaseContext(base)
    DoraIronSource.setAppKey("填入你的Iron Source App Key")
}
```

在AndroidManifest中加入配置。
```xml
<application>
        <!-- Dora生命周期注入的配置 -->
        <meta-data
            android:name="dora.lifecycle.config.IronSourceGlobalConfig"
            android:value="GlobalConfig"/>
</application>
```

要显示banner广告的Activity加入代码。
```kotlin
override fun onResume() {
    super.onResume()
    IronSource.onResume(this)
}

override fun onPause() {
    super.onPause()
    IronSource.onPause(this)
}
```

可设置回调接口。
```kotlin
IronSource.addImpressionDataListener {
}
```

xml布局。
```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">
    
    <!-- 此处省略原先的业务代码若干行...
        容器的宽高修改为
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_weight="1" -->
    
    <!-- 要加入的部分开始 -->
    <LinearLayout
        android:id="@+id/banner_view_layout"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        android:gravity="bottom"
        android:orientation="vertical">

        <FrameLayout
            android:id="@+id/banner_footer"
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:layout_gravity="center"
            android:visibility="gone"/>
    </LinearLayout>
    <!-- 要加入的部分结束 -->
</LinearLayout>
```

展示和销毁广告。
```kotlin
// 伪代码，实际请设置为成员属性
val listener = DoraBannerViewListener(binding.bannerFooter)
// 展示横幅广告
listener.loadBanner()
// 销毁横幅广告
listener.destroyBanner()
```
