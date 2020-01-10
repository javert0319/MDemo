package com.nbhope.chia.viewpager

import android.support.v4.view.ViewPager
import android.view.View

/**
 * @ClassName: AlphaTransformer
 * @Description: 作用描述
 * @Author: CHIA
 * @CreateDate: 2020/1/3
 */
class AlphaTransformer : ViewPager.PageTransformer {

    private val MINALPHA = 0.5f

    /**
     * position取值特点：
     * 假设页面从0～1，则：
     * 第一个页面position变化为[0,-1]
     * 第二个页面position变化为[1,0]
     *
     * @param page
     * @param position
     */
    override fun transformPage(page: View, position: Float) {
        if (position < -1 || position > 1) {
            page.alpha = MINALPHA
        } else {
            //不透明->半透明
            if (position < 0) {//[0,-1]
                page.alpha = MINALPHA + (1 + position) * (1 - MINALPHA)
            } else {//[1,0]
                //半透明->不透明
                page.alpha = MINALPHA + (1 - position) * (1 - MINALPHA)
            }
        }
    }
}
