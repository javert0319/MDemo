package com.kit.chia;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.transition.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.kit.chia.eventbas.EventBusHelper;

/**
 * @ClassName: BaseActivity
 * @Description: Activity 基类
 * @Author: CHIA
 * @CreateDate: 2019/12/27
 */
public abstract class BaseActivity extends AppCompatActivity implements ICreate{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVarAndView(getLayoutInflater(), null, savedInstanceState);
        initTransition();
        midfield();
        initEvent();
        initDoing();
    }

    //用于继承自BaseActivity的基类 进行一些初始化，一般情况下，不用重写
    public void midfield() {}

    //初始化事件
    public void initEvent() {}

    //开始执行
    public void initDoing() {}

    private void initTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //getWindow().setEnterTransition(new Slide(Gravity.RIGHT));
            //getWindow().setExitTransition(new Slide(Gravity.RIGHT));
            Slide slideIn = new Slide();
            slideIn.setDuration(200);
            slideIn.setMode(Visibility.MODE_IN);
            slideIn.setSlideEdge(android.view.Gravity.RIGHT);
            getWindow().setEnterTransition(slideIn);

            Slide slideReturn = new Slide();
            slideReturn.setDuration(200);
            slideReturn.setSlideEdge(android.view.Gravity.RIGHT);
            slideReturn.setMode(Visibility.MODE_OUT);
            getWindow().setReturnTransition(slideReturn);
        }
    }

    @Override
    public final View initVarAndView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initVarAndView(savedInstanceState);
        return null;
    }

    //初始化变量和界面
    public abstract void initVarAndView(Bundle savedInstanceState);

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private EventBusHelper eventBusHelper;

    public void initEventBus() {
        if (eventBusHelper == null) {
            eventBusHelper = new EventBusHelper();
        }
        eventBusHelper.setEnable(true);
        eventBusHelper.register(this);
    }

    @Override
    protected void onDestroy() {
        if (eventBusHelper != null) {
            eventBusHelper.unregister(this);
        }
        super.onDestroy();
    }
}
