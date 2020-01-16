package com.kit.chia.pipeta

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kit.chia.R
import android.util.DisplayMetrics
import android.widget.RelativeLayout.LayoutParams
import kotlinx.android.synthetic.main.activity_seekbar.*
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.kit.chia.utils.SPUtil
import com.kit.chia.widget.CustomSeekBar

class SeekBarActivity : AppCompatActivity() {

    private var width: Double = 0.0
    private var fDensity: Double = 0.0
    private var numbers = 45

    private var displaysMetrics: DisplayMetrics? = null

    private var customSeekBar:CustomSeekBar ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seekbar)
        /*numbers = SPUtil.getInt(this,"number",45)
        displaysMetrics = resources.displayMetrics
        width = displaysMetrics?.widthPixels?.toDouble()!!
        fDensity = (width - dip2px(this, 21.0f)) / 100*/

        //initSeekBarProgress()

        customSeekBar = CustomSeekBar(this,textView1,seekBar1)
        customSeekBar?.init()

        btn_get_number.setOnClickListener {
            val data = customSeekBar?.getTextData()
            tv_seekbar_data.text = data
        }
    }

    private fun initSeekBarProgress() {
        seekBar1.progress = numbers
        seekBar1.setOnSeekBarChangeListener(mSeekChange)
        val paramsStrength = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        paramsStrength.leftMargin = (numbers * fDensity).toInt()
        textView1.layoutParams = paramsStrength
        textView1.text = numbers.toString() + "元"
    }

    private val mSeekChange = object : OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            numbers = progress
            val paramsStrength = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            paramsStrength.leftMargin = (progress * fDensity).toInt()
            textView1.layoutParams = paramsStrength
            textView1.text = numbers.toString() + "元"
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {}

        override fun onStopTrackingTouch(seekBar: SeekBar) {}
    }

    override fun onDestroy() {
        super.onDestroy()
        SPUtil.putInt(this,"number",numbers)
    }
}
