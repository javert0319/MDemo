package com.nbhope.chia

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.nbhope.chia.brvah.BrvahActivity
import com.nbhope.chia.brvah.ChoiceActivity
import com.nbhope.chia.brvah.DimmerActivity
import com.nbhope.chia.brvah.SelectedActivity
import com.nbhope.chia.cache.CacheActivity
import com.nbhope.chia.dialog.DialogActivity
import com.nbhope.chia.immersion.ImmersionActivity
import com.nbhope.chia.pipeta.MoveActivity
import com.nbhope.chia.pipeta.PipetaActivity
import com.nbhope.chia.pipeta.SeekBarActivity
import com.nbhope.chia.rxjava.RxJavaActivity
import com.nbhope.chia.viewpager.ViewPagerActivity
import kotlinx.android.synthetic.main.activity_main.*

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
            }
            if (targetClass != null){
                val intent = Intent(this@MainActivity,targetClass)
                intent.putExtra("from",0)
                startActivity(intent)
            }
        }
    }
}
