package com.nbhope.chia.viewpager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import com.nbhope.chia.R
import com.nbhope.chia.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_view_pager.*
import java.util.ArrayList

class ViewPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        initRecyclerView()
        initViewPager()
    }

    private fun initRecyclerView() {
        val datas = ArrayList<MusicMultiBean>()
        for (i in 0..20){
            datas.add(MusicMultiBean("李健$i",R.mipmap.breakfast_radiobutton_checked,1))
        }
        val adapter = MusicMultiAdapter(datas)
        val manager = LinearLayoutManager(this)
        rv_music.layoutManager = manager
        rv_music.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        rv_music.adapter = adapter
        adapter.setOnItemClickListener { adapter, view, position ->
            val bean = adapter.getItem(position) as MusicMultiBean
            ToastUtils.toast(this,bean.name)
        }
    }

    private fun initViewPager() {
        val inflater = LayoutInflater.from(this)
        //设置ViewPager中两页之间的距离
        //viewpager.pageMargin = 80

        //  设置预加载的页数，我们知道默认情况下这个参数为1，也就是左右各预加载一页，但是我们这里要让左右各预加载两页
        //viewpager.offscreenPageLimit = 3
        val list = ArrayList<MusicBean>()
        for (i in 0..20){
            list.add(MusicBean("Artist$i","Song$i",R.mipmap.breakfast_radiobutton_checked))
        }
        val adater = MusicPagerAdapter(this, list,inflater)
        viewpager.adapter = adater
        //viewpager.setPageTransformer(false, AlphaTransformer())
        //viewpager.setCurrentItem(3,false)
        //adater.notifyDataSetChanged()
        viewpager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
                Log.i("jiawei","ViewPagerActivity initViewPager onPageScrollStateChanged $state")
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                Log.i("jiawei","ViewPagerActivity initViewPager onPageScrolled $position")
            }

            override fun onPageSelected(position: Int) {
                Log.i("jiawei","ViewPagerActivity initViewPager onPageSelected ${list[position].artist}")

            }
        })
    }
}
