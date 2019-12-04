package com.nbhope.chia.cache.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import com.nbhope.chia.utils.MD5Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @ClassName: LocalCacheUtils
 * @Description: 本地缓存
 * @Author: CHIA
 * @CreateDate: 2019/12/4
 */
public class LocalCacheUtils {

    // 文件保存的路径
    public static final String FILE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/cache/pics";

    //从本地SD卡获取网络图片，key是url的MD5值
    public static Bitmap getBitmapFromLocal(String url) throws FileNotFoundException {
        String fileName = MD5Util.md5(url);
        File file = new File(FILE_PATH,fileName);
        if (file.exists()){
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            return bitmap;
        }
        return null;
    }

    //向本地SD卡写网络图片
    public static void setBitmap2Local(String url,Bitmap bitmap) throws FileNotFoundException {
        String fileName = MD5Util.md5(url);
        File file = new File(FILE_PATH,fileName);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()){
            parentFile.mkdirs();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(file));
    }
}
