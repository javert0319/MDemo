package com.nbhope.chia

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.nbhope.chia.brvah.BrvahActivity
import com.nbhope.chia.brvah.CheckBoxActivity
import com.nbhope.chia.cache.CacheActivity
import com.nbhope.chia.rxjava.RxJavaActivity
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
        data.add("CHECKBOX")
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
                3 -> targetClass = CheckBoxActivity::class.java
            }
            if (targetClass != null){
                val intent = Intent(this@MainActivity,targetClass)
                intent.putExtra("from",0)
                startActivity(intent)
            }
        }
    }
}
