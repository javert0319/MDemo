package com.kit.chia.netease

import android.os.Bundle
import android.util.Log
import com.kit.chia.BaseActivity
import com.kit.chia.R
import com.kit.chia.utils.StatusBarUtils
import com.kit.discview.DiscView
import com.kit.discview.model.MusicData
import kotlinx.android.synthetic.main.activity_netease.*

class MusicTempActivity : BaseActivity(), DiscView.IPlayInfo {

    private var mDiscView: DiscView?= null
    private val mMusicDatas = ArrayList<MusicData>()

    override fun initVarAndView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_music_temp)
        StatusBarUtils.with(this).init()
    }

    override fun initDoing() {
        super.initDoing()
        initMusicDatas()
        mDiscView = findViewById<DiscView>(R.id.discview)
        setSupportActionBar(toolBar)
        mDiscView?.setMusicDataList(mMusicDatas)
        //mDiscView?.setMusicRes(R.raw.ic_music1)
        mDiscView?.setPlayInfoListener(this)
        ivPlayOrPause.setOnClickListener {
            mDiscView?.playOrPause()
        }
        ivLast.setOnClickListener {
            mDiscView?.last()
        }
        ivNext.setOnClickListener {
            mDiscView?.next()
        }
    }

    private fun initMusicDatas() {
        val musicData1 = MusicData(R.raw.music1, R.raw.ic_music1, "寻", "三亩地")
        val musicData2 = MusicData(R.raw.music2, R.raw.ic_music2, "Nightingale", "YANI")
        val musicData3 = MusicData(R.raw.music3, R.raw.ic_music3, "Cornfield Chase", "Hans Zimmer")

        mMusicDatas.add(musicData1)
        mMusicDatas.add(musicData2)
        mMusicDatas.add(musicData3)
    }

    override fun onMusicInfoChanged(musicName: String?, musicAuthor: String?) {
        Log.i("jiawei","MusicTempActivity onMusicInfoChanged $musicName $musicAuthor")
    }

    override fun onMusicPicChanged(musicPicRes: Int) {

    }

    override fun onMusicChanged(musicChangedStatus: DiscView.MusicChangedStatus?) {
        Log.i("jiawei","MusicTempActivity onMusicChanged $musicChangedStatus")
    }
}
