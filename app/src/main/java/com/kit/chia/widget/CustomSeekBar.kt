package com.kit.chia.widget

import android.content.Context
import android.util.DisplayMetrics
import android.widget.RelativeLayout.LayoutParams
import android.widget.SeekBar
import android.widget.TextView
import com.kit.chia.utils.DensityUtil

/**
 * @ClassName: CustomSeekBar
 * @Description: 自定义带文字描述的SeekBar
 * @Author: CHIA
 * @CreateDate: 2019/12/23
 */
class CustomSeekBar(private val mContext: Context, private val textView: TextView, private val seekBar: SeekBar) {

    private var width: Double = 0.0
    private var fDensity: Double = 0.0
    private var numbers = 45
    private var displaysMetrics: DisplayMetrics? = null

    fun init() {
        displaysMetrics = mContext.resources.displayMetrics
        width = displaysMetrics?.widthPixels?.toDouble()!!
        fDensity = (width - DensityUtil.dip2px(mContext, 21.0f)) / 100
        seekBar.progress = numbers
        seekBar.setOnSeekBarChangeListener(mSeekChange)
        val paramsStrength = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        paramsStrength.leftMargin = (numbers * fDensity).toInt()
        textView.layoutParams = paramsStrength
        textView.text = numbers.toString() + "元"
    }

    fun getTextData(): String {
        return textView.text.toString()
    }

    private val mSeekChange = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
            numbers = progress
            val paramsStrength = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            )
            paramsStrength.leftMargin = (progress * fDensity).toInt()
            textView.layoutParams = paramsStrength
            textView.text = numbers.toString() + "元"
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {}

        override fun onStopTrackingTouch(seekBar: SeekBar) {}
    }
}
