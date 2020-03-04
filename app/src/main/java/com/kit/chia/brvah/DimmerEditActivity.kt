package com.kit.chia.brvah

import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.kit.chia.BaseActivity
import com.kit.chia.R
import com.kit.chia.brvah.bean.DimmerBean
import com.kit.chia.eventbas.Activation
import kotlinx.android.synthetic.main.activity_dimmer_edit.*
import org.greenrobot.eventbus.EventBus

@Route(path = "/mdemo/dimmer_edit")
class DimmerEditActivity : BaseActivity() {

    @Autowired
    @JvmField
    var dimmer: DimmerBean = DimmerBean()

    override fun initVarAndView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_dimmer_edit)
        ARouter.getInstance().inject(this)

        Log.i("jiawei","DimmerEditActivity flag: ${dimmer.actionName}")
        et_dimmer_action.setText("桥边姑娘")

        btn_dimmer_edit.setOnClickListener {
            dimmer.actionName = et_dimmer_action.text.toString()
            EventBus.getDefault().post(Activation(Activation.ON_DIMMER_EDIT,dimmer))
            finish()
        }
    }
}
