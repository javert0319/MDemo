package com.kit.chia.netease

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.os.Bundle
import com.kit.chia.BaseActivity
import com.kit.chia.R
import com.kit.chia.utils.GlobalParameters
import com.kit.chia.netease.service.MusicService
import android.content.Intent
import android.os.Handler
import android.os.Message
import android.support.v4.content.LocalBroadcastManager
import com.kit.chia.netease.model.MusicData
import kotlinx.android.synthetic.main.activity_netease.*
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.BitmapDrawable
import android.widget.SeekBar
import com.kit.chia.netease.widget.BackgourndAnimationRelativeLayout
import com.kit.chia.utils.StatusBarUtils
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.RequiresApi
import com.kit.chia.netease.utils.DisplayUtil
import com.kit.chia.netease.utils.FastBlurUtil
import com.kit.chia.netease.widget.DiscView
import com.kit.chia.netease.widget.DiscView.DURATION_NEEDLE_ANIAMTOR
import com.kit.chia.netease.widget.DiscView.MusicChangedStatus.*
import com.kit.chia.utils.ToastUtils


class NeteaseActivity : BaseActivity() , DiscView.IPlayInfo{

    private var mDisc: DiscView? = null
    private var mRootLayout: BackgourndAnimationRelativeLayout?= null

    private val mMusicReceiver = MusicReceiver()
    private val mMusicDatas = ArrayList<MusicData>()

    private val mMusicHandler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            musicSeekBar.progress = musicSeekBar.progress + 1000
            tvTotalTime.text = duration2Time(musicSeekBar.progress)
            startUpdateSeekBarProgress()
        }
    }

    override fun initVarAndView(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_netease)
        StatusBarUtils.with(this).init()
        GlobalParameters.setPARAM_MUSIC_LIST("PARAM_MUSIC_LIST")
    }

    override fun initDoing() {
        super.initDoing()
        initMusicDatas()
        mDisc = findViewById<DiscView>(R.id.discview)
        mRootLayout = findViewById(R.id.rootLayout)
        setSupportActionBar(toolBar)
        initMusicReceiver()
        musicSeekBar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvCurrentTime.text = duration2Time(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                stopUpdateSeekBarProgree()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                seekTo(seekBar?.progress)
                startUpdateSeekBarProgress()
            }

        })
        tvCurrentTime.text = duration2Time(0)
        tvTotalTime.text = duration2Time(0)
        mDisc?.setMusicDataList(mMusicDatas)
        mDisc?.setPlayInfoListener(this)

        ivPlayOrPause.setOnClickListener {
            mDisc?.playOrPause()
        }
        ivLast.setOnClickListener {
            mDisc?.last()
        }
        ivNext.setOnClickListener {
            //mDisc?.next()
            startActivity(Intent(this,MusicTempActivity::class.java))
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun try2UpdateMusicPicBackground(musicPicRes:Int){
        if (mRootLayout?.isNeed2UpdateBackground(musicPicRes)!!){
            Thread(Runnable {
                val foregroundDrawable = getForegroundDrawable(musicPicRes)
                runOnUiThread {
                    mRootLayout?.foreground = foregroundDrawable
                    mRootLayout?.beginAnimation()
                }
            }).start()
        }
    }

    private fun getForegroundDrawable(musicPicRes: Int): Drawable? {
        /*得到屏幕的宽高比，以便按比例切割图片一部分*/
        val widthHeightSize = (DisplayUtil.getScreenWidth(this) * 1.0 / DisplayUtil.getScreenHeight(this) * 1.0).toFloat()
        val bitmap = getForegroundBitmap(musicPicRes)

        val cropBitmapWidth = (widthHeightSize * bitmap.height).toInt()
        val cropBitmapWidthX = ((bitmap.width - cropBitmapWidth) / 2.0).toInt()
        /*切割部分图片*/
        val cropBitmap = Bitmap.createBitmap(bitmap, cropBitmapWidthX, 0, cropBitmapWidth, bitmap.height)
        /*缩小图片*/
        val scaleBitmap = Bitmap.createScaledBitmap(cropBitmap, bitmap.width / 50, bitmap.height / 50, false)
        /*模糊化*/
        val blurBitmap = FastBlurUtil.doBlur(scaleBitmap, 8, true)

        val foregroundDrawable = BitmapDrawable(blurBitmap)
        /*加入灰色遮罩层，避免图片过亮影响其他控件*/
        foregroundDrawable.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY)
        return foregroundDrawable
    }

    private fun getForegroundBitmap(musicPicRes: Int):Bitmap {
        val screenWidth = DisplayUtil.getScreenWidth(this)
        val screenHeight = DisplayUtil.getScreenHeight(this)

        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true

        BitmapFactory.decodeResource(resources, musicPicRes, options)
        val imageWidth = options.outWidth
        val imageHeight = options.outHeight

        if (imageWidth < screenWidth && imageHeight < screenHeight) {
            return BitmapFactory.decodeResource(resources, musicPicRes)
        }

        var sample = 2
        val sampleX = imageWidth / DisplayUtil.getScreenWidth(this)
        val sampleY = imageHeight / DisplayUtil.getScreenHeight(this)

        if (sampleX > sampleY && sampleY > 1) {
            sample = sampleX
        } else if (sampleY > sampleX && sampleX > 1) {
            sample = sampleY
        }

        options.inJustDecodeBounds = false
        options.inSampleSize = sample
        options.inPreferredConfig = Bitmap.Config.RGB_565

        return BitmapFactory.decodeResource(resources, musicPicRes, options)
    }

    private fun seekTo(position: Int?) {
        val intent = Intent(MusicService.ACTION_OPT_MUSIC_SEEK_TO)
        intent.putExtra(MusicService.PARAM_MUSIC_SEEK_TO, position)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }

    private fun initMusicReceiver() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(MusicService.ACTION_STATUS_MUSIC_PLAY)
        intentFilter.addAction(MusicService.ACTION_STATUS_MUSIC_PAUSE)
        intentFilter.addAction(MusicService.ACTION_STATUS_MUSIC_DURATION)
        intentFilter.addAction(MusicService.ACTION_STATUS_MUSIC_COMPLETE)
        /*注册本地广播*/
        LocalBroadcastManager.getInstance(this).registerReceiver(mMusicReceiver, intentFilter)
    }

    private fun initMusicDatas() {
        val musicData1 = MusicData(R.raw.music1, R.raw.ic_music1, "寻", "三亩地")
        val musicData2 = MusicData(R.raw.music2, R.raw.ic_music2, "Nightingale", "YANI")
        val musicData3 = MusicData(R.raw.music3, R.raw.ic_music3, "Cornfield Chase", "Hans Zimmer")

        mMusicDatas.add(musicData1)
        mMusicDatas.add(musicData2)
        mMusicDatas.add(musicData3)

        val intent = Intent(this, MusicService::class.java)
        intent.putExtra(GlobalParameters.getPARAM_MUSIC_LIST(), mMusicDatas)
        startService(intent)
    }

    inner class MusicReceiver:BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            val action = intent?.action
            when(action) {
                MusicService.ACTION_STATUS_MUSIC_PLAY -> {
                    ivPlayOrPause.setImageResource(R.drawable.ic_pause)
                    val currentPosition = intent.getIntExtra(MusicService.PARAM_MUSIC_CURRENT_POSITION, 0)
                    musicSeekBar.progress = currentPosition
                    if (!mDisc?.isPlaying!!){
                        mDisc?.playOrPause()
                    }
                }
                MusicService.ACTION_STATUS_MUSIC_PAUSE -> {
                    ivPlayOrPause.setImageResource(R.drawable.ic_play)
                    if (mDisc?.isPlaying!!){
                        mDisc?.playOrPause()
                    }
                }
                MusicService.ACTION_STATUS_MUSIC_DURATION -> {
                    val duration = intent.getIntExtra(MusicService.PARAM_MUSIC_DURATION, 0)
                    updateMusicDurationInfo(duration)
                }
                MusicService.ACTION_STATUS_MUSIC_COMPLETE -> {
                    val isOver = intent.getBooleanExtra(MusicService.PARAM_MUSIC_IS_OVER, true)
                    complete(isOver)
                }
            }
        }
    }

    private fun complete(over: Boolean) {
        if (over){
            mDisc?.stop()
        }else{
            mDisc?.next()
        }
    }

    private fun updateMusicDurationInfo(duration: Int) {
        musicSeekBar.progress = 0
        musicSeekBar.max = duration
        tvTotalTime.text = duration2Time(duration)
        tvCurrentTime.text = duration2Time(0)
        startUpdateSeekBarProgress()
    }

    private fun startUpdateSeekBarProgress() {
        /*避免重复发送Message*/
        stopUpdateSeekBarProgree()
        mMusicHandler.sendEmptyMessageDelayed(0,1000)
    }

    private fun stopUpdateSeekBarProgree() {
        mMusicHandler.removeMessages(GlobalParameters.getMUSIC_MESSAGE())
    }

    /*根据时长格式化称时间文本*/
    private fun duration2Time(duration: Int): String {
        val min = duration / 1000 / 60
        val sec = duration / 1000 % 60

        return (if (min < 10) "0$min" else min.toString() + "") + ":" + if (sec < 10) "0$sec" else sec.toString() + ""
    }

    override fun onMusicInfoChanged(musicName: String?, musicAuthor: String?) {
        supportActionBar?.title = musicName
        supportActionBar?.subtitle = musicAuthor
        ToastUtils.toast(this,musicName)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onMusicPicChanged(musicPicRes: Int) {
        try2UpdateMusicPicBackground(musicPicRes)
    }

    override fun onMusicChanged(musicChangedStatus: DiscView.MusicChangedStatus?) {
        ToastUtils.toast(this, "status: $musicChangedStatus")
        when(musicChangedStatus){
            PLAY -> play()
            PAUSE -> pause()
            NEXT -> next()
            LAST -> last()
            STOP -> stop()
        }
    }

    private fun stop() {
        stopUpdateSeekBarProgree();
        ivPlayOrPause.setImageResource(R.drawable.ic_play)
        tvCurrentTime.text = duration2Time(0)
        tvTotalTime.text = duration2Time(0)
        musicSeekBar.progress = 0
    }

    private fun last() {
        mRootLayout?.postDelayed({
            optMusic(MusicService.ACTION_OPT_MUSIC_LAST)
        }, DURATION_NEEDLE_ANIAMTOR.toLong())
        stopUpdateSeekBarProgree()
        tvCurrentTime.text = duration2Time(0)
        tvTotalTime.text = duration2Time(0)
    }

    private fun next(){
        mRootLayout?.postDelayed({
            optMusic(MusicService.ACTION_OPT_MUSIC_NEXT)
        }, DURATION_NEEDLE_ANIAMTOR.toLong())
        stopUpdateSeekBarProgree()
        tvCurrentTime.text = duration2Time(0)
        tvTotalTime.text = duration2Time(0)
    }

    private fun pause() {
        optMusic(MusicService.ACTION_OPT_MUSIC_PAUSE)
        stopUpdateSeekBarProgree()
    }

    private fun play() {
        optMusic(MusicService.ACTION_OPT_MUSIC_PLAY)
        startUpdateSeekBarProgress()
    }

    private fun optMusic(action: String) {
        LocalBroadcastManager.getInstance(this).sendBroadcast(Intent(action))
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMusicReceiver)
    }
}
