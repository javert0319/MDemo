package com.kit.chia.netease

import android.os.Bundle
import android.util.Log
import com.kit.chia.BaseActivity
import com.kit.chia.R
import com.kit.chia.utils.ToastUtils
import com.kit.chia.widget.Lock9View
import kotlinx.android.synthetic.main.activity_lock_9_view.*

class Lock9ViewActivity : BaseActivity() {

    override fun initVarAndView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_lock_9_view)
    }

    override fun initDoing() {
        super.initDoing()

        lock_9_view.setGestureCallback(object :Lock9View.GestureCallback{
            override fun onNodeConnected(numbers: IntArray) {
                ToastUtils.toast(this@Lock9ViewActivity,"+ " + numbers[numbers.size - 1])
            }

            override fun onGestureFinished(numbers: IntArray) {
                val builder = StringBuilder()
                for (number in numbers) {
                    builder.append(number)
                }
                ToastUtils.toast(this@Lock9ViewActivity,"= " + builder.toString())
                Log.i("jiawei","Lock9ViewActivity setGestureCallback: ${builder.toString()}")
            }

        })
    }

}
