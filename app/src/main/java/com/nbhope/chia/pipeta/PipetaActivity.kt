package com.nbhope.chia.pipeta

import android.Manifest
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MotionEvent
import com.nbhope.chia.R
import kotlinx.android.synthetic.main.activity_pipeta.*
import android.graphics.BitmapFactory
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.load.engine.Resource
import com.nbhope.chia.utils.NetUtils
import com.tbruyelle.rxpermissions2.RxPermissions

class PipetaActivity : AppCompatActivity() {

    private var bitmap: Bitmap?= null
    private val NONE:Int = 0
    private val PHOTOHRAPH:Int = 1
    private val PHOTOZOOM:Int = 2
    private val PHOTORESOULT:Int = 3
    private val IMAGE_UNSPECIFIED:String = "image/*"
    private val TEMP_JPG_NAME:String = "temp.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pipeta)

        btnColor.setOnClickListener {
            openAlbum()
        }

        bitmap = BitmapFactory.decodeResource(this.resources, R.mipmap.img_rank)
        iv_image.setImageBitmap(bitmap)
        iv_image.setOnTouchListener { v, event ->
            val moveX = event.x.toInt()
            val moveY = event.y.toInt()
            if (event.action == MotionEvent.ACTION_MOVE){
                if (moveX > 0 && moveY > 0 && moveX < bitmap!!.width && moveY < bitmap!!.height){
                    val color = bitmap?.getPixel(moveX,moveY)
                    // 如果你想做的更细致的话 可以把颜色值的R G B 拿到做响应的处理
                    val red = Color.red(color!!)
                    val green = Color.green(color)
                    val blue = Color.blue(color)
                    val alpha = Color.alpha(color)
                    Log.i("jiawei","PipetaActivity RGB: ${event.x} ${event.y}")
                    textview.setTextColor(color)
                    textview.text = "PipetaActivity RGB: $red $green $blue $alpha"

                    iv_pot.x = event.rawX
                    iv_pot.y = event.rawY
                }
            }
            true
        }

        RxPermissions(this).request(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION).subscribe({ b ->
            if (b) {
                val ssid = NetUtils.getWIFISSID(this)
                Log.i("jiawei","PipetaActivity RxPermissions $ssid")
            }
        }, { e -> })
    }


    /**
     * 通过drawingCache获取bitmap
     *
     * @param view
     * @return
     */
    private fun convertViewToBitmap2(view: View): Bitmap {
        view.isDrawingCacheEnabled = true
        val bitmap = Bitmap.createBitmap(view.drawingCache)
        //如果不调用这个方法，每次生成的bitmap相同
        view.isDrawingCacheEnabled = false
        return bitmap
    }

    private fun openAlbum() {
        val intent = Intent(Intent.ACTION_PICK, null)
        intent.setDataAndType(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            "image/*")
        startActivityForResult(intent, PHOTOZOOM)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == NONE)return
        if (data == null)return
        if (requestCode == PHOTOZOOM){
            if (data != null) {
                startPhotoZoom(data.data)
            }
        }
        if (requestCode == PHOTORESOULT) {
            val extras = data.extras
            if (extras != null) {
                bitmap = extras.getParcelable("data")
                //ByteArrayOutputStream stream = new ByteArrayOutputStream();
                comp(bitmap!!)
                //bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                iv_image.setImageBitmap(bitmap)
                /* logoName = FileUtils.getFilename(MainAppUtil.getCustom().getSusername());
                 FileUtils.writeFile(Constants.LOGO_CACHE_PATH, logoName, photo);*/
            }
        }
    }

    private fun comp(bitmap: Bitmap): Bitmap {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        if (baos.toByteArray().size / 1024 > 1024) {
            //判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset();//重置baos即清空baos
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
            //这里压缩50%，把压缩后的数据存放到baos中
        }
        var isBm = ByteArrayInputStream(baos.toByteArray())
        val newOpts = BitmapFactory.Options()
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true
        var bitmap = BitmapFactory.decodeStream(isBm, null, newOpts)
        newOpts.inJustDecodeBounds = false
        val w = newOpts.outWidth
        val h = newOpts.outHeight
        //现在主流手机比较多是800*500分辨率，所以高和宽我们设置为
        val hh = 800f//这里设置高度为800f
        val ww = 500f//这里设置宽度为500f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        var be = 1//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (newOpts.outWidth / ww) as Int
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (newOpts.outHeight / hh) as Int
        }
        if (be <= 0)
            be = 1
        newOpts.inSampleSize = be//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = ByteArrayInputStream(baos.toByteArray())
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts)
        return compressImage(bitmap)//压缩好比例大小后再进行质量压缩
    }

    private fun compressImage(bitmap: Bitmap): Bitmap {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        var options = 100
        while (baos.toByteArray().size / 1024 > 100) { //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset()//重置baos即清空baos
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos)//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10//每次都减少10
        }
        val isBm = ByteArrayInputStream(baos.toByteArray())//把压缩后的数据baos存放到ByteArrayInputStream中
        return BitmapFactory.decodeStream(isBm, null, null)
    }

    private fun startPhotoZoom(uri: Uri) {
        val intent = Intent("com.android.camera.action.CROP")
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED)
        intent.putExtra("crop", "true")
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1)
        intent.putExtra("aspectY", 1)
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300)
        intent.putExtra("outputY", 300)
        intent.putExtra("return-data", true)
        startActivityForResult(intent, PHOTORESOULT)
    }
}
