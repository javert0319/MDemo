package com.nbhope.chia

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ArrayAdapter
import com.alibaba.android.arouter.launcher.ARouter
import com.nbhope.chia.brvah.BrvahActivity
import com.nbhope.chia.brvah.ChoiceActivity
import com.nbhope.chia.brvah.SelectedActivity
import com.nbhope.chia.cache.CacheActivity
import com.nbhope.chia.rxjava.RxJavaActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference

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
            }
            if (targetClass != null){
                val intent = Intent(this@MainActivity,targetClass)
                intent.putExtra("from",0)
                startActivity(intent)
            }
        }
    }
}
