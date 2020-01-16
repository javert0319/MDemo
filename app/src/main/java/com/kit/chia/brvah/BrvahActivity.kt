package com.kit.chia.brvah

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.kit.chia.R
import com.kit.chia.brvah.adapter.MultiItemAdapter
import com.kit.chia.brvah.bean.MtestEntity
import kotlinx.android.synthetic.main.activity_brvah.*


class BrvahActivity : AppCompatActivity() {

    private var mAdapter:MultiItemAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brvah)

        initMultiContent()
    }

    private fun initMultiContent() {
        val item = MtestEntity()
        item.name = "yang"
        item.type = 1
        val item1 = MtestEntity()
        item1.name = "yangxixi"
        item1.type = 3
        val item2 = MtestEntity()
        item2.name = "fangfeizi"
        item2.type = 2
        val list = ArrayList<MtestEntity>()
        list.add(item)
        list.add(item)
        list.add(item)
        list.add(item)
        list.add(item)
        list.add(item)

        list.add(item1)
        list.add(item1)

        list.add(item2)
        list.add(item2)
        list.add(item2)

        list.add(item1)
        list.add(item1)

        list.add(item2)
        list.add(item2)
        list.add(item2)

        mAdapter = MultiItemAdapter(list)
        val manager = GridLayoutManager(this, 6)


        rv_bravh.layoutManager = manager
        rv_bravh.adapter = mAdapter

        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (position < 6){
                    return 6
                }else if (position in 6..7){
                    return 3
                }else if (position in 8..10){
                    return 2
                }else if (position in 11..12){
                    return 3
                } else {
                    return 2
                }
            }
        }
    }
}