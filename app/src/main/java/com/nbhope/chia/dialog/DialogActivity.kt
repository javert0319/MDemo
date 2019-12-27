package com.nbhope.chia.dialog

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.nbhope.chia.R
import com.nbhope.chia.dialog.style.CommonDialog
import com.nbhope.chia.dialog.style.ConfirmDialog
import kotlinx.android.synthetic.main.activity_dialog.*

class DialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        btn_dialog_style1.setOnClickListener {
            val dialog = MaterialDialog.Builder(this)
                .customView(R.layout.dialog_share, true).build()
            dialog.show()
            val customView = dialog.customView
            customView?.findViewById<TextView>(R.id.tv_weixin_share)?.setOnClickListener {
                dialog.dismiss()
            }
        }
        btn_dialog_style2.setOnClickListener {
            val dialog = MaterialDialog.Builder(this)
                .title("提示")
                .content("MateriaDialog simple")
                .positiveText("确定")
                .negativeText("取消")
                .onPositive { dialog, which ->
                    dialog.dismiss()
                }.build()
            dialog.show()
        }
        btn_dialog_style3.setOnClickListener {

        }
    }
}
