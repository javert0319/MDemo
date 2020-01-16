package com.kit.chia.viewpager

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kit.chia.R
import com.kit.chia.utils.ToastUtils

import java.util.ArrayList

/**
 * @ClassName: MusicPagerAdapter
 * @Description: 作用描述
 * @Author: CHIA
 * @CreateDate: 2020/1/3
 */
class MusicPagerAdapter(val mContext: Context, val mDatas: List<MusicBean>, val inflater: LayoutInflater) : PagerAdapter() {

    private val views = ArrayList<View>()

    override fun getCount(): Int {
        Log.i("jiawei","MusicPagerAdapter getCount: ${mDatas.size}")
        return mDatas.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = inflater.inflate(R.layout.music_item_play_bar, null, false)
        view.tag = position
        if (mDatas.isNotEmpty()) {
            val musicBean = mDatas[position]
            val mIcon = view.findViewById<ImageView>(R.id.img_kit_play_album)
            val title = view.findViewById<TextView>(R.id.tv_kit_play_bar_title)
            val artist = view.findViewById<TextView>(R.id.tv_kit_play_bar_artist)
            mIcon.scaleType = ImageView.ScaleType.FIT_XY
            mIcon.setImageResource(musicBean.icon)
            title.text = musicBean.song
            artist.text = musicBean.artist
        }
        view.setOnClickListener {
            ToastUtils.toast(mContext, mDatas[position].song + "--" + mDatas[position].artist)
        }
        container.addView(view)
        views.add(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        //super.destroyItem(container, position, object);
        if (views.size > position) {
            container.removeView(views[position])
        }
        container.removeView(`object` as View)
    }
}
