package edu.xtu.androidbase.weaher.util.Retrofit;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import edu.xtu.androidbase.weaher.BuildConfig;
import edu.xtu.androidbase.weaher.util.AppInfo;
import edu.xtu.androidbase.weaher.util.DeviceUtils;
import edu.xtu.androidbase.weaher.util.LogUtils;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by huilin on 2016/11/6.
 */
public class RetrofitClient {
    public static String BASE_URL= "https://api.heweather.com/x3/";
    private OkHttpClient okHttpClient;
    private String NET_CACHE = "";
    public Retrofit retrofit;
    private RetrofitClient(){
        this.NET_CACHE = AppInfo.getAppInstant().getMyContext().getExternalCacheDir().toString()+"/net_cache";
        init();
    }

    private void init() {
        initOkClient();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    public static RetrofitClient getInstance(){
        return RetrofitHolder.INSTANCR;
    }

    public static class RetrofitHolder{
        public static  RetrofitClient INSTANCR = new RetrofitClient();
    }

    private void initOkClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if(BuildConfig.IS_DEBUG){
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        //无网络也能看到，设置网络缓存
        File cacheFile  = new File(NET_CACHE);
        if(cacheFile!=null){
            Cache cache = new Cache(cacheFile,1024 * 1024 * 50);
            builder.cache(cache).addInterceptor(getCacheSetting());
        }
        //设置公共参数
        builder.addInterceptor(getCommonRequestParem());
        //设置超时
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        okHttpClient = builder.build();

    }

    /**
     * 设置缓存
     * @return
     */
    private Interceptor getCacheSetting(){
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                Response response = chain.proceed(request);
                if(DeviceUtils.getInstance().isNetworkConnected()){
                    //联网设置缓存为0
                    // 有网络时 设置缓存超时时间0个小时
                    int maxAge = 0;
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .build();
                }else{
                    // 无网络时，设置超时为4周
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                    response = chain.proceed(request);
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .build();
                }

                return response;
            }

        };
        return interceptor;
    }

    /**
     * 公共参数
     * @return
     */
    private Interceptor getCommonRequestParem(){
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request request;
                HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                        // Provide your custom parameter here
                        .addQueryParameter("platform", "android")
                        .addQueryParameter("version", AppInfo.getAppInstant().getVersionName())
                        .addQueryParameter("versionCode",""+AppInfo.getAppInstant().getVersionCode())
                        .build();
                request = originalRequest.newBuilder().url(modifiedUrl).build();
                LogUtils.i("okHttpUrl",request.url().toString());
                return chain.proceed(request);
            }
        };
        return  interceptor;
    }

}
