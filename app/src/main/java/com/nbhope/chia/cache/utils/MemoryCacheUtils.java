package com.nbhope.chia.cache.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * @ClassName: MemoryCacheUtils
 * @Description: 内存缓存
 * @Author: CHIA
 * @CreateDate: 2019/12/4
 */
public class MemoryCacheUtils {
    /*
     * 由于map默认是强引用，全部在JVM进行垃圾回收的时候不会回收map的引用
     */
    // private HashMap<String, Bitmap> map = new HashMap<String, Bitmap>();
    // 软引用的实例,在内存不够时。垃圾回收器会优先考虑回收
    // private HashMap<String, SoftReference<Bitmap>> mSoftReferenceMap = new
    // HashMap<String, SoftReference<Bitmap>>();
    // LruCache
    private LruCache<String, Bitmap> lruCache;

    public MemoryCacheUtils() {
        // lruCache最大同意内存一般为Android系统分给每一个应用程序内存大小（默认Android系统给每一个应用程序分配16兆内存）的八分之中的一个（推荐）
        // 获得当前应用程序运行的内存大小
        long mCurrentMemory = Runtime.getRuntime().maxMemory();
        int maxSize = (int) (mCurrentMemory / 8);
        // 给LruCache设置最大的内存
        lruCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                // 获取每张图片所占内存的大小
                // 计算方法是：图片显示的宽度的像素点乘以高度的像素点
                int byteCount = value.getRowBytes() * value.getHeight();// 获取图片占用内存大小
                return byteCount;
            }
        };
    }

    /**
     * 从内存中读取Bitmap
     *
     * @param url
     * @return
     */
    public Bitmap getBitmapFromMemory(String url) {

        // Bitmap bitmap = map.get(url);
        // SoftReference<Bitmap> softReference = mSoftReferenceMap.get(url);
        // Bitmap bitmap = softReference.get();
        // 软引用在Android2.3以后就不推荐使用了，Google推荐使用lruCache
        // LRU--least recently use
        // 近期最少使用,将内存控制在一定的大小内。超过这个内存大小，就会优先释放近期最少使用的那些东东
        Bitmap bitmap = lruCache.get(url);
        return bitmap;

    }

    /**
     * 将图片保存到内存中
     *
     * @param url
     * @param bitmap
     */
    public void setBitmap2Memory(String url, Bitmap bitmap) {
        // 向内存中设置，key,value的形式，首先想到HashMap
        // map.put(url, bitmap);
        // 保存软引用到map中
        // SoftReference<Bitmap> mSoftReference = new
        // SoftReference<Bitmap>(bitmap);
        // mSoftReferenceMap.put(url, mSoftReference);
        lruCache.put(url, bitmap);
    }

}
