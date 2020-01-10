package com.nbhope.chia.viewpager

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.nbhope.chia.R
import com.nbhope.chia.brvah.bean.MtestEntity

/**
 * @ClassName: MusicMultiAdapter
 * @Description: 作用描述
 * @Author: CHIA
 * @CreateDate: 2020/1/3
 */
class MusicMultiAdapter
/**
 * Same as QuickAdapter#QuickAdapter(Context,int) but with
 * some initialization data.
 *
 * @param data A new list is created out of this one to avoid mutable list
 */
    (data: List<MusicMultiBean>) : BaseMultiItemQuickAdapter<MusicMultiBean, BaseViewHolder>(data) {

    init {
        addItemType(1, R.layout.bravh_item_music)
    }

    override fun convert(helper: BaseViewHolder, item: MusicMultiBean) {
        when (helper.itemViewType) {
            1 -> {
                helper.setImageResource(R.id.iv_bravh_icon, item.icon)
                helper.setText(R.id.tv_bravh_txt, item.name)
            }
            else -> {
            }
        }
    }
}
