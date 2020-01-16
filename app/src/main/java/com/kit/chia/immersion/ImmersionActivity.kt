package com.kit.chia.immersion

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.kit.chia.R
import com.kit.chia.utils.NetUtils
import kotlinx.android.synthetic.main.activity_immersion.*

class ImmersionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_immersion)

        btn_immersion.setOnClickListener {
            ARouter.getInstance().build("/mdemo/immersion")
                .withString("type","none")
                .navigation()
        }
        btn_immersion_actionbar.setOnClickListener {
            ARouter.getInstance().build("/mdemo/immersion_mall")
                .withString("type","actionBar")
                .navigation()
            Log.i("jiawei","ImmersionActivity Random ${NetUtils.getRandom(20)}")
        }
        btn_immersion_image.setOnClickListener {
            ARouter.getInstance().build("/mdemo/immersion")
                .withString("type","image")
                .navigation()
        }
        btn_immersion_nva.setOnClickListener {
            ARouter.getInstance().build("/mdemo/immersion")
                .withString("type","nva")
                .navigation()
        }
    }


}
