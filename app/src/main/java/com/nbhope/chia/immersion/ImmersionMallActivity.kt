package com.nbhope.chia.immersion

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.nbhope.chia.BaseActivity
import com.nbhope.chia.R
import com.nbhope.chia.utils.StatusBarUtils
import kotlinx.android.synthetic.main.activity_immersion_mall.*

@Route(path = "/mdemo/immersion_mall")
class ImmersionMallActivity : BaseActivity() {

    override fun initVarAndView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_immersion_mall)
        StatusBarUtils.with(this).init()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
