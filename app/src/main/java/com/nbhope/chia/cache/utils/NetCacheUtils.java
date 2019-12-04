package com.nbhope.chia.cache.utils;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nbhope.chia.App;
import com.nbhope.chia.R;
import com.nbhope.chia.utils.NetUtils;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: NetCacheUtils
 * @Description: 网络缓存
 * @Author: CHIA
 * @CreateDate: 2019/12/4
 */
public class NetCacheUtils {

    private static LocalCacheUtils localCacheUtils;
    private static MemoryCacheUtils memoryCacheUtils;

    public NetCacheUtils() {
        localCacheUtils = new LocalCacheUtils();
        memoryCacheUtils = new MemoryCacheUtils();
    }

    //有网时候的缓存
    static final Interceptor netCacheInterceptor = new Interceptor() {
        @NotNull
        @Override
        public Response intercept(@NotNull Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            int onlineCacheTime = 30;//在线的时候的缓存过期时间(30秒)，如果想要不缓存，直接时间设置为0
            return response.newBuilder()
                    .header("Cache-Control", "public, max-age="+onlineCacheTime)
                    .removeHeader("Pragma")
                    .build();
        }
    };

    //没网时候的缓存
    static final Interceptor offlineCacheInterceptor = new Interceptor() {
        @NotNull
        @Override
        public Response intercept(@NotNull Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtils.isNetworkAvailable(App.getInstance())){
                int offlineCacheTime = 60;//离线的时候的缓存的过期时间
                request = request.newBuilder()
//                        .cacheControl(new CacheControl
//                                .Builder()
//                                .maxStale(60,TimeUnit.SECONDS)
//                                .onlyIfCached()
//                                .build()
//                        ) 两种方式结果是一样的，写法不同
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + offlineCacheTime)
                        .build();
            }
            return chain.proceed(request);
        }
    };

    //setup cache
    static File sdCard = Environment.getExternalStorageDirectory();
    static File httpCacheDirectory = new File(sdCard + File.separator, "okhttpCache");
    static int cacheSize = 10 * 1024 * 1024; // 10 MiB
    static Cache cache = new Cache(httpCacheDirectory, cacheSize);
    static OkHttpClient client = new OkHttpClient.Builder()
            .addNetworkInterceptor(netCacheInterceptor)
            .addInterceptor(offlineCacheInterceptor)
            .cache(cache)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build();

    public static void getNetData(String url,Callback callback){
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }

    public void getBitmapFromNet(final String url, final ImageView pic){
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.i("jiawei","网络请求失败");
            }

            @SuppressLint("CheckResult")
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();//得到图片的流
                final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                Handler mainHandler = new Handler(Looper.getMainLooper());
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("jiawei","从网络获取图片");
                        pic.setImageBitmap(bitmap);
                        try {
                            // 从网络载入完之后。将图片保存到本地SD卡一份，保存到内存中一份
                            localCacheUtils.setBitmap2Local(url, bitmap);
                            // 从网络载入完之后。将图片保存到本地SD卡一份，保存到内存中一份
                            memoryCacheUtils.setBitmap2Memory(url, bitmap);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

}
