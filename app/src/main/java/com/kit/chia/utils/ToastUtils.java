package com.kit.chia.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @ClassName: ToastUtils
 * @Description: 作用描述
 * @Author: CHIA
 * @CreateDate: 2020/1/3
 */
public class ToastUtils {

    public static void toast(Context context,String content){
        Toast.makeText(context,content, Toast.LENGTH_SHORT).show();
    }
}
