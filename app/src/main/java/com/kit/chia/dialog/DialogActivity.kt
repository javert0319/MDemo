package com.kit.chia.dialog

import android.app.Dialog
import android.app.DialogFragment
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.afollestad.materialdialogs.MaterialDialog
import com.kit.chia.R
import kotlinx.android.synthetic.main.activity_dialog.*
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.RelativeLayout

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
            val ft = fragmentManager.beginTransaction()
            val newFragment = MyDialogFragment.newInstance()
            newFragment.show(ft, "dialog")
        }
    }

    class MyDialogFragment : DialogFragment() {

        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater?.inflate(R.layout.fragment_dialog, container, false)
        }

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            // the content
            val root = RelativeLayout(activity)
            root.layoutParams =
                    ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

            // creating the fullscreen dialog
            val dialog = Dialog(activity)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(root)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

            return dialog
        }
        companion object {

            internal fun newInstance(): MyDialogFragment {
                return MyDialogFragment()
            }
        }
    }
}
