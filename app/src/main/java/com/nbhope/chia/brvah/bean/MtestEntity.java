package com.nbhope.chia.brvah.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @ClassName: MtestEntity
 * @Description: 作用描述
 * @Author: CHIA
 * @CreateDate: 2019/12/3
 */
public class MtestEntity implements MultiItemEntity {

    public String name;
    public int type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
