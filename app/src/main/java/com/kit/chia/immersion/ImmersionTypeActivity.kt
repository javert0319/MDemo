package com.kit.chia.immersion

import android.os.Bundle
import android.util.Log
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.kit.chia.BaseActivity
import com.kit.chia.R
import com.kit.chia.utils.StatusBarUtils
import kotlinx.android.synthetic.main.activity_immersion_type.*

@Route(path = "/mdemo/immersion")
class ImmersionTypeActivity : BaseActivity() {

    @Autowired
    @JvmField
    var type:String = ""

    override fun initVarAndView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_immersion_type)
        ARouter.getInstance().inject(this)
    }

    override fun initDoing() {
        super.initDoing()
        when(type){
            "none" -> {
                Log.i("jiawei","ImmersionTypeActivity type none: $type")
                rl_immersion_none.visibility = View.VISIBLE
                StatusBarUtils.with(this)
                    .setColor(resources.getColor(R.color.blue))
                    .init()
            }
            "image" -> {
                Log.i("jiawei","ImmersionTypeActivity type image: $type")
                rl_immersion_image.visibility = View.VISIBLE
                StatusBarUtils.with(this).init()
            }
            "actionBar" -> {
                Log.i("jiawei","ImmersionTypeActivity type actionBar: $type")
                rl_immersion_actionbar.visibility = View.VISIBLE
                StatusBarUtils.with(this)
                    .setIsActionBar(true)
                    .clearActionBarShadow()
                    .setDrawable(resources.getDrawable(R.drawable.immersion_shape))
                    .init()
            }
            "nva" -> {
                Log.i("jiawei","ImmersionTypeActivity type nva: $type")

            }
        }
    }

    override fun initEvent() {
        super.initEvent()
        iv_immersion_back.setOnClickListener {
            finish()
        }
    }
}
