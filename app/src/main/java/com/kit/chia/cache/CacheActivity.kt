package com.kit.chia.cache

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kit.chia.R
import com.kit.chia.cache.utils.BitmapUtils
import kotlinx.android.synthetic.main.activity_cache.*

class CacheActivity : AppCompatActivity() {

    private val imgUrl:String = "http://wimg.spriteapp.cn/picture/2019/1127/5dde1e22485fe_wpd.jpg"

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cache)
        btn_cache_net.setOnClickListener {
            BitmapUtils.getInstance().display(imgUrl,iv_cache_net)
            /*NetCacheUtils.getNetData(imgUrl, object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    //请求失败
                }

                @Throws(IOException::class)
                override fun onResponse(call: Call, response: Response) {
                    //请求成功
                    val string = response.body!!.string()
                    Log.i("jiawei","CacheActivity getNetImage $string")
                    if (!TextUtils.isEmpty(string)) {
                        val gson = Gson()
                        val cacheBean = gson.fromJson<CacheBean>(string, CacheBean::class.java)

                        //数据操作
                    }
                }
            })*/
        }
    }
}
