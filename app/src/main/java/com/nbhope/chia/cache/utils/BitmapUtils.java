package com.nbhope.chia.cache.utils;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;
import com.nbhope.chia.R;

import java.io.FileNotFoundException;

/**
 * @ClassName: BitmapUtils
 * @Description: 三级缓存封装
 * @Author: CHIA
 * @CreateDate: 2019/12/4
 */
public class BitmapUtils {

    private Bitmap bitmap;

    private static NetCacheUtils netCacheUtils;
    private static LocalCacheUtils localCacheUtils;
    private static MemoryCacheUtils memoryCacheUtils;

    public static BitmapUtils getInstance(){
        return SingleTonHolder.instance;
    }

    private static class SingleTonHolder {
        private static BitmapUtils instance = new BitmapUtils();
    }

    public static void init() {
        netCacheUtils = new NetCacheUtils();
        localCacheUtils = new LocalCacheUtils();
        memoryCacheUtils = new MemoryCacheUtils();
    }

    /**
     * 载入图片，将当前URL相应的图片显示到ivPic的控件上
     *
     * @param ivPic
     *            ImageView控件
     * @param url
     *            图片的地址
     */
    public void display(String url,ImageView ivPic) throws FileNotFoundException {
        // 设置默认显示的图片
        ivPic.setImageResource(R.mipmap.ic_launcher);

        // 1、内存缓存
        bitmap = memoryCacheUtils.getBitmapFromMemory(url);
        if (bitmap != null) {
            ivPic.setImageBitmap(bitmap);
            Log.i("jiawei","从内存缓存中载入图片");
            return;
        }
        // 2、本地磁盘缓存
        bitmap = localCacheUtils.getBitmapFromLocal(url);
        if (bitmap != null) {
            ivPic.setImageBitmap(bitmap);
            Log.i("jiawei","从本地SD卡载入的图片");
            memoryCacheUtils.setBitmap2Memory(url, bitmap);// 将图片保存到内存
            return;
        }
        // 3、网络缓存
        netCacheUtils.getBitmapFromNet(url,ivPic);
        /*
         * 从网络获取图片之后。将图片保存到手机SD卡中，在进行图片展示的时候，优先从SD卡中读取缓存,key是图片的URL的MD5值。
         * value是保存的图片bitmap
         */
    }
}
