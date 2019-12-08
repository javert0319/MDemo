package com.nbhope.chia;

import android.app.Application;
import com.nbhope.chia.cache.utils.BitmapUtils;

/**
 * @ClassName: App
 * @Description: 作用描述
 * @Author: CHIA
 * @CreateDate: 2019/12/4
 */
public class App extends Application {

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        BitmapUtils.init();
    }
}
