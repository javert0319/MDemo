package com.nbhope.chia.rxjava

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.nbhope.chia.R
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.internal.operators.flowable.FlowableConcatMap.subscribe
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rxjava.*

class RxJavaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxjava)

        btn_rxjava.setOnClickListener {
            getRxJavaData()
        }

    }

    @SuppressLint("CheckResult")
    private fun getRxJavaData() {


    }
}
