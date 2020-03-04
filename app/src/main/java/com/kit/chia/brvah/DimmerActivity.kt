package com.kit.chia.brvah

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.google.gson.Gson
import com.kit.chia.BaseActivity
import com.kit.chia.R
import com.kit.chia.brvah.adapter.DimmerAdapter
import com.kit.chia.brvah.bean.DimmerBean
import com.kit.chia.eventbas.Activation
import kotlinx.android.synthetic.main.activity_dimmer.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class DimmerActivity : BaseActivity() {

    private var lists =  ArrayList<DimmerBean>()
    private var dimmerAdapter:DimmerAdapter ?= null

    override fun initVarAndView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_dimmer)
        ARouter.getInstance().inject(this)
        initEventBus()
        initView()
        initEvent()
        for (i in 0..19){
            val selectI = i
        }
    }

    override fun initDoing() {
        val dimmerBean0 = DimmerBean()
        dimmerBean0.dimmerName = "看到这个效果，看到很多人用ViewFlipper实现，但是效果并不理想，于是我想到用RecyclerView试试"
        dimmerBean0.isSub = false
        lists.add(dimmerBean0)

        val dimmerBean1 = DimmerBean()
        dimmerBean1.dimmerName = "看到这个效果，看到很多人用ViewFlipper实现，但是效果并不理想，于是我想到用RecyclerView试试"
        dimmerBean1.isSub = false
        lists.add(dimmerBean1)

        val dimmerBean2 = DimmerBean()
        dimmerBean2.dimmerName = "看到这个效果，看到很多人用ViewFlipper实现，但是效果并不理想，于是我想到用RecyclerView试试"
        dimmerBean2.isSub = false
        lists.add(dimmerBean2)

        val dimmerBean3 = DimmerBean()
        dimmerBean3.dimmerName = "看到这个效果，看到很多人用ViewFlipper实现，但是效果并不理想，于是我想到用RecyclerView试试"
        dimmerBean3.isSub = false
        lists.add(dimmerBean3)

        val dimmerBean4 = DimmerBean()
        dimmerBean4.dimmerName = "看到这个效果，看到很多人用ViewFlipper实现，但是效果并不理想，于是我想到用RecyclerView试试"
        dimmerBean4.isSub = false
        lists.add(dimmerBean4)

        val dimmerBean5 = DimmerBean()
        dimmerBean5.dimmerName = "看到这个效果，看到很多人用ViewFlipper实现，但是效果并不理想，于是我想到用RecyclerView试试"
        dimmerBean5.isSub = false
        lists.add(dimmerBean5)

        val dimmerBean6 = DimmerBean()
        dimmerBean6.dimmerName = "看到这个效果，看到很多人用ViewFlipper实现，但是效果并不理想，于是我想到用RecyclerView试试"
        dimmerBean6.isSub = false
        lists.add(dimmerBean6)

        val dimmerBean7 = DimmerBean()
        dimmerBean7.dimmerName = "看到这个效果，看到很多人用ViewFlipper实现，但是效果并不理想，于是我想到用RecyclerView试试"
        dimmerBean7.isSub = false
        lists.add(dimmerBean7)

        val dimmerBean8 = DimmerBean()
        dimmerBean8.dimmerName = "看到这个效果，看到很多人用ViewFlipper实现，但是效果并不理想，于是我想到用RecyclerView试试"
        dimmerBean8.isSub = false
        lists.add(dimmerBean8)

        val dimmerBean11 = DimmerBean()
        dimmerBean11.dimmerName = "看到这个效果，看到很多人用ViewFlipper实现，但是效果并不理想，于是我想到用RecyclerView试试"
        dimmerBean11.isSub = true
        lists.add(dimmerBean11)

        val dimmerBean12 = DimmerBean()
        dimmerBean12.dimmerName = "看到这个效果，看到很多人用ViewFlipper实现，但是效果并不理想，于是我想到用RecyclerView试试"
        dimmerBean12.isSub = true
        lists.add(dimmerBean12)

        val dimmerBean13 = DimmerBean()
        dimmerBean13.dimmerName = "看到这个效果，看到很多人用ViewFlipper实现，但是效果并不理想，于是我想到用RecyclerView试试"
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
            val dimmerBean = adapter.getItem(position) as DimmerBean
            if (dimmerBean.isSub){
                ARouter.getInstance().build("/mdemo/dimmer_edit")
                    .withSerializable("dimmer", dimmerBean)
                    .navigation()
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onDimmerEventBus(event:Activation){
        if (event.compare(Activation.ON_DIMMER_EDIT)){
            val dimmer = event.obj1 as DimmerBean
            for (i in 0..lists.size){
                lists[i].actionName = null
                if (lists[i].dimmerName == dimmer.dimmerName){
                    lists[i] = dimmer
                    dimmerAdapter?.notifyDataSetChanged()

                }
                Log.i("jiawei","DimmerActivity onDimmerEventBus ${Gson().toJson(lists[i])}")
            }
        }
    }
}
