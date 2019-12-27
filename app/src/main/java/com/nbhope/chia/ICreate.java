package com.nbhope.chia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @ClassName: ICreate
 * @Description: 作用描述
 * @Author: CHIA
 * @CreateDate: 2019/12/27
 */
public interface ICreate {
    //初始化变量和界面
    View initVarAndView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    //用于继承自BaseActivity的基类 进行一些初始化，一般情况下，不用重写
    void midfield();

    //初始化事件
    void initEvent();

    //开始执行
    void initDoing();
}
