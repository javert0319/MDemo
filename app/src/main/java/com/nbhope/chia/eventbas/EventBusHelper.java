package com.nbhope.chia.eventbas;

import org.greenrobot.eventbus.EventBus;

/**
 * @ClassName: EventBusHelper
 * @Description: EventBus 帮助类
 * @Author: CHIA
 * @CreateDate: 2019/12/27
 */
public class EventBusHelper {

    private boolean enable = false;
    private boolean isRegister = false;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void register(Object subscriber){
        if (enable && !isRegister){
            EventBus.getDefault().register(subscriber);
            isRegister = true;
        }
    }

    public void unregister(Object subscriber){
        if (enable){
            EventBus.getDefault().unregister(subscriber);
        }
    }
}
