package com.nbhope.chia.brvah

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.nbhope.chia.R
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import com.nbhope.chia.brvah.adapter.ChoiceAdapter
import com.nbhope.chia.brvah.bean.ChoiceBean
import kotlinx.android.synthetic.main.activity_choice.*


class ChoiceActivity : AppCompatActivity() {

    var mStaggeredGridLayoutManager: StaggeredGridLayoutManager? = null
    var mAdapter:ChoiceAdapter ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice)

        val manager = LinearLayoutManager(this)
        rv_choice.layoutManager = manager
        //mStaggeredGridLayoutManager = StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL)
        //rv_choice.layoutManager = mStaggeredGridLayoutManager
        mAdapter = ChoiceAdapter(initDatas(),this,rv_choice)
        rv_choice.adapter = mAdapter

        /*rv_choice.post {
            val firstVisibleItemPositions = mStaggeredGridLayoutManager?.findFirstVisibleItemPositions(null)
            val lastVisibleItemPositions = mStaggeredGridLayoutManager?.findLastVisibleItemPositions(null)
            Log.d("zxt", "onCreate() called with: firstVisibleItemPositions = [" + firstVisibleItemPositions!![0] + "]");

            Log.d("zxt", "onCreate() called with: lastVisibleItemPositions = [" + lastVisibleItemPositions!![lastVisibleItemPositions.size - 1] + "]")
        }*/
    }

    fun initDatas(): List<ChoiceBean> {
        val datas = ArrayList<ChoiceBean>()
        for (i in 0..19) {
            datas.add(ChoiceBean("满100减99"))
            datas.add(ChoiceBean("满100减98", if (i == 0) true else false))
            datas.add(ChoiceBean("满100减97"))
            datas.add(ChoiceBean("满100减96"))
            datas.add(ChoiceBean("满100减95"))
            datas.add(ChoiceBean("满100减94"))
            datas.add(ChoiceBean("满100减93"))
            datas.add(ChoiceBean("满100减92"))
            datas.add(ChoiceBean("满100减91"))
            datas.add(ChoiceBean("满100减90"))
        }
        return datas
    }
}
