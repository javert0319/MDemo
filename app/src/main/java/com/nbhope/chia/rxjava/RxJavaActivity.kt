package com.nbhope.chia.rxjava

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.nbhope.chia.R
import com.nbhope.chia.utils.GsonUtils
import kotlinx.android.synthetic.main.activity_rxjava.*

class RxJavaActivity : AppCompatActivity() {

    private val command:String = "[{\"musId\":31,\"musName\":\"北国之春\"}]"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava)

        btn_rxjava.setOnClickListener {
            getRxJavaData()
        }

    }

    private fun getRxJavaData() {
        val list = GsonUtils.jsonToList(command)
        for (bean in list){
            Log.i("jiawei","RxJavaActivity getRxJavaData: ${bean.musId} ${bean.musName}")
        }
    }
}
