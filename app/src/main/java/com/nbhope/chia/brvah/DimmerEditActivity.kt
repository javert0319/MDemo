package com.nbhope.chia.brvah

import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.nbhope.chia.BaseActivity
import com.nbhope.chia.R
import com.nbhope.chia.eventbas.Activation
import kotlinx.android.synthetic.main.activity_dimmer_edit.*
import org.greenrobot.eventbus.EventBus

@Route(path = "/mdemo/dimmer_edit")
class DimmerEditActivity : BaseActivity() {

    @Autowired
    @JvmField
    var flag: String = ""

    private var extraName:String ?= null

    override fun initVarAndView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_dimmer_edit)
        ARouter.getInstance().inject(this)

        Log.i("jiawei","DimmerEditActivity flag: $flag")
        extraName = intent.getStringExtra("flag")
        et_dimmer_action.setText(extraName)

        btn_dimmer_edit.setOnClickListener {
            extraName = et_dimmer_action.text.toString()

            EventBus.getDefault().post(Activation(Activation.ON_DIMMER_EDIT,extraName))

            /*val intent = Intent()
            intent.putExtra("action",extraName)
            setResult(Activity.RESULT_OK,intent)*/
            finish()
        }
    }
}
