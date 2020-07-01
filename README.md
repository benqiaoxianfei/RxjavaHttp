# RxjavaHttp

在build.gradle里面设置
```java
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
  然后添加依赖
```java
  dependencies {
		implementation 'com.github.benqiaoxianfei:RxjavaHttp:0.0.1'
	}
```

这是一个对Rxjava2.0+retrofit2.0+okhttp3.0的网络库封装

需要自己定义retrofit2 的接口

```java
public interface AppServices {
    @GET("")
    Observable getImage(@Query("n") int n);
```

如果要使用加载弹出框 需要添加权限
```java
<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"/>
```
有些真机型 申请了权限也是不管用  需要在手机的设置里面找到后台弹出界面的权限 允许就可以了
