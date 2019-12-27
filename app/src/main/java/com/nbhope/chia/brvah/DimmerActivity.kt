package com.nbhope.chia.brvah

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.nbhope.chia.BaseActivity
import com.nbhope.chia.R
import com.nbhope.chia.brvah.adapter.DimmerAdapter
import com.nbhope.chia.brvah.bean.DimmerBean
import com.nbhope.chia.eventbas.Activation
import kotlinx.android.synthetic.main.activity_dimmer.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class DimmerActivity : BaseActivity() {

    private var lists =  ArrayList<DimmerBean>()
    private var dimmerAdapter:DimmerAdapter ?= null
    private var curPosition = 0

    override fun initVarAndView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_dimmer)
        ARouter.getInstance().inject(this)
        initEventBus()
        initView()
        initEvent()
    }

    override fun initDoing() {
        /*for (i in 0..19){
            if (i>16){
                val dimmerBean = DimmerBean()
                dimmerBean.dimmerName = "播放：$i"
                dimmerBean.isSub = true
                lists.add(dimmerBean)
            }else{
                val dimmerBean = DimmerBean()
                dimmerBean.dimmerName = "播放：$i"
                lists.add(dimmerBean)
            }
        }*/
        val dimmerBean0 = DimmerBean()
        dimmerBean0.dimmerName = "播放：0"
        lists.add(dimmerBean0)

        val dimmerBean1 = DimmerBean()
        dimmerBean1.dimmerName = "播放：1"
        lists.add(dimmerBean1)

        val dimmerBean2 = DimmerBean()
        dimmerBean2.dimmerName = "播放：2"
        lists.add(dimmerBean2)

        val dimmerBean3 = DimmerBean()
        dimmerBean3.dimmerName = "播放：3"
        lists.add(dimmerBean3)

        val dimmerBean4 = DimmerBean()
        dimmerBean4.dimmerName = "播放：4"
        lists.add(dimmerBean4)

        val dimmerBean5 = DimmerBean()
        dimmerBean5.dimmerName = "播放：5"
        lists.add(dimmerBean5)

        val dimmerBean6 = DimmerBean()
        dimmerBean6.dimmerName = "播放：6"
        lists.add(dimmerBean6)

        val dimmerBean7 = DimmerBean()
        dimmerBean7.dimmerName = "播放：7"
        lists.add(dimmerBean7)

        val dimmerBean8 = DimmerBean()
        dimmerBean8.dimmerName = "播放：8"
        lists.add(dimmerBean8)

        val dimmerBean9 = DimmerBean()
        dimmerBean9.dimmerName = "播放：9"
        lists.add(dimmerBean9)

        val dimmerBean10 = DimmerBean()
        dimmerBean10.dimmerName = "播放：10"
        lists.add(dimmerBean10)

        val dimmerBean11 = DimmerBean()
        dimmerBean11.dimmerName = "播放：11"
        dimmerBean11.isSub = true
        lists.add(dimmerBean11)

        val dimmerBean12 = DimmerBean()
        dimmerBean12.dimmerName = "播放：12"
        dimmerBean12.isSub = true
        lists.add(dimmerBean12)

        val dimmerBean13 = DimmerBean()
        dimmerBean13.dimmerName = "播放：13"
        dimmerBean13.isSub = true
        lists.add(dimmerBean13)
    }

    private fun initView() {
        val linearLayoutManager = LinearLayoutManager(this)
        rv_dimmer_list.layoutManager = linearLayoutManager
        dimmerAdapter = DimmerAdapter(R.layout.item_dimmer_layout,lists)
        rv_dimmer_list.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        rv_dimmer_list.adapter = dimmerAdapter
    }

    override fun initEvent() {
        dimmerAdapter?.setOnItemClickListener { adapter, view, position ->
            curPosition = position
            val dimmerBean = adapter.getItem(position) as DimmerBean
            if (dimmerBean.isSub){
                ARouter.getInstance().build("/mdemo/dimmer_edit")
                    .withString("flag",dimmerBean.actionName?:"桥边姑娘")
                    .navigation()
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onDimmerEventBus(event:Activation){
        if (event.compare(Activation.ON_DIMMER_EDIT)){
            val content = event.arg1
            Log.i("jiawei","DimmerActivity onDimmerEventBus: $content")
            val bean = lists[curPosition]
            val dimmerBean: DimmerBean
            dimmerBean = bean
            dimmerBean.actionName = content
            dimmerAdapter?.notifyItemChanged(curPosition)
        }
    }
}
