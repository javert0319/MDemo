package com.kit.chia

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.kit.chia.brvah.BrvahActivity
import com.kit.chia.brvah.ChoiceActivity
import com.kit.chia.brvah.DimmerActivity
import com.kit.chia.brvah.SelectedActivity
import com.kit.chia.cache.CacheActivity
import com.kit.chia.dialog.DialogActivity
import com.kit.chia.immersion.ImmersionActivity
import com.kit.chia.netease.NeteaseActivity
import com.kit.chia.pipeta.MoveActivity
import com.kit.chia.pipeta.PipetaActivity
import com.kit.chia.pipeta.SeekBarActivity
import com.kit.chia.rxjava.RxJavaActivity
import com.kit.chia.viewpager.ViewPagerActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_list?.adapter = ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, getData())
    }

    private fun getData(): ArrayList<String> {
        val data = ArrayList<String>()
        data.add("RxJava")
        data.add("BRAVH")
        data.add("缓存")
        data.add("单选")
        data.add("单选、全选、反选")
        data.add("RGB取色")
        data.add("MOVE")
        data.add("DialogFragment")
        data.add("Dimmer")
        data.add("SeekBar")
        data.add("ViewPager")
        data.add("沉浸式")
        data.add("网易云音乐播放")
        return data
    }

    override fun onStart() {
        super.onStart()
        rv_list?.setOnItemClickListener { parent, view, position, id ->
            var targetClass:Class<*> ?= null
            when(position){
                0 -> targetClass = RxJavaActivity::class.java
                1 -> targetClass = BrvahActivity::class.java
                2 -> targetClass = CacheActivity::class.java
                3 -> targetClass = ChoiceActivity::class.java
                4 -> targetClass = SelectedActivity::class.java
                5 -> targetClass = PipetaActivity::class.java
                6 -> targetClass = MoveActivity::class.java
                7 -> targetClass = DialogActivity::class.java
                8 -> targetClass = DimmerActivity::class.java
                9 -> targetClass = SeekBarActivity::class.java
                10 -> targetClass = ViewPagerActivity::class.java
                11 -> targetClass = ImmersionActivity::class.java
                12 -> targetClass = NeteaseActivity::class.java
            }
            if (targetClass != null){
                val intent = Intent(this@MainActivity,targetClass)
                intent.putExtra("from",0)
                startActivity(intent)
            }
        }
    }
}
