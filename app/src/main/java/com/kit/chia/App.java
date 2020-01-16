package com.kit.chia;

import android.app.Application;
import com.alibaba.android.arouter.launcher.ARouter;
import com.kit.chia.cache.utils.BitmapUtils;

/**
 * @ClassName: App
 * @Description: 作用描述
 * @Author: CHIA
 * @CreateDate: 2019/12/4
 */
public class App extends Application {

    private boolean isDebugARouter = true;
    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        BitmapUtils.init();
        if (isDebugARouter){
            ARouter.openLog();//打印日志
            ARouter.openDebug();//线上版本需要关闭
        }
        ARouter.init(this);
    }
}
