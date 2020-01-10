package com.nbhope.chia.viewpager

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * @ClassName: MusicMultiBean
 * @Description: 作用描述
 * @Author: CHIA
 * @CreateDate: 2020/1/3
 */
class MusicMultiBean(var name: String?, var icon: Int, var type: Int) : MultiItemEntity {

    override fun getItemType(): Int {
        return type
    }
}
