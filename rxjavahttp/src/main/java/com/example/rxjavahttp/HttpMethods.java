package com.example.rxjavahttp;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HttpMethods {
    private static final long DEFAULT_TIME = 6L;//网络超时时间
    private Retrofit retrofit;
    private static final String TAG = HttpMethods.class.getSimpleName();
    private String mUrl;
    private static HttpMethods httpMethods;

    private HttpMethods(String url) {
        this.mUrl = url;
        retrofit = new Retrofit.Builder()
                .baseUrl(mUrl)
                .client(getOkHttpClient())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static HttpMethods getInstance(String url) {
        if (httpMethods == null) {
            httpMethods = new HttpMethods(url);
        }
        return httpMethods;
    }

    private OkHttpClient getOkHttpClient() {
        //日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e(TAG, "OkHttp====Message:" + message);
            }
        });
        loggingInterceptor.setLevel(level);
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient
                .Builder()
                .connectTimeout(DEFAULT_TIME, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIME, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIME, TimeUnit.SECONDS);
        //OkHttp进行添加拦截器loggingInterceptor
        httpClientBuilder.addInterceptor(loggingInterceptor);
        return httpClientBuilder.build();
    }

    public <T> T createService(Class<T> clazz) {
        return retrofit.create(clazz);
    }

}
