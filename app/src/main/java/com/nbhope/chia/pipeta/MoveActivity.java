package com.nbhope.chia.pipeta;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.nbhope.chia.MainActivity;
import com.nbhope.chia.R;
import com.skydoves.colorpickerview.ColorListener;
import com.skydoves.colorpickerview.ColorPickerView;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.warkiz.tickseekbar.OnSeekChangeListener;
import com.warkiz.tickseekbar.SeekParams;
import com.warkiz.tickseekbar.TickSeekBar;

public class MoveActivity extends AppCompatActivity {

    private final int GPS_REQUEST_CODE = 0x001;

    private ColorPickerView colorPickerView;
    private TextView tvColor;
    private DisplayMetrics displaysMetrics;
    private double width, fDensity;
    private int numbers=30;
    private TextView num_tv;
    private SeekBar seekBar;
    private TickSeekBar tickSeekBar;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);

        colorPickerView = findViewById(R.id.colorPickerView);
        tvColor = findViewById(R.id.tv_rgb);

        colorPickerView.setColorListener(new ColorListener() {
            @Override
            public void onColorSelected(int color) {
                int red = Color.red(color);
                int green = Color.green(color);
                int blue = Color.blue(color);
                int alpha = Color.alpha(color);

                tvColor.setText("RGB: "+red+", "+green+", " + blue);
                tvColor.setTextColor(color);
            }
        });

        initView();
        initSeekBarProgress();

        /*RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION);*/
        openGPSSEtting();


    }

    private boolean checkGpsIsOpen() {
        boolean isOpen;
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        isOpen = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isOpen;
    }

    private void openGPSSEtting() {
        if (checkGpsIsOpen()){
            Toast.makeText(this, "true", Toast.LENGTH_SHORT).show();
        }else {
            new AlertDialog.Builder(this).setTitle("open GPS")
                    .setMessage("go to open")
                    //  取消选项
                    .setNegativeButton("cancel",new DialogInterface.OnClickListener(){

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(MoveActivity.this, "close", Toast.LENGTH_SHORT).show();
                            // 关闭dialog
                            dialogInterface.dismiss();
                        }
                    })
                    //  确认选项
                    .setPositiveButton("setting", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //跳转到手机原生设置页面
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivityForResult(intent,GPS_REQUEST_CODE);
                        }
                    })
                    .setCancelable(false)
                    .show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==GPS_REQUEST_CODE){
            openGPSSEtting();
        }
    }

    private void initSeekBarProgress() {
        seekBar.setProgress(numbers);
        seekBar.setOnSeekBarChangeListener(mSeekChange);
        LinearLayout.LayoutParams paramsStrength = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsStrength.leftMargin = (int) (numbers * fDensity);
        num_tv.setLayoutParams(paramsStrength);
        num_tv.setText("速度-"+numbers);

        tickSeekBar.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {
                Log.i("Z-MoveActivity", "seekBar" + seekParams.seekBar);
                Log.i("Z-MoveActivity", "progress" + seekParams.progress);
                Log.i("Z-MoveActivity", "progressFloat" + seekParams.progressFloat);
                Log.i("Z-MoveActivity", "fromUser" + seekParams.fromUser);
                //when tick count > 0
                Log.i("Z-MoveActivity", "thumbPosition" + seekParams.thumbPosition);
                Log.i("Z-MoveActivity", "tickText" + seekParams.tickText);
            }

            @Override
            public void onStartTrackingTouch(TickSeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(TickSeekBar seekBar) {
            }

        });
    }

    private SeekBar.OnSeekBarChangeListener mSeekChange = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            numbers = progress;
            LinearLayout.LayoutParams paramsStrength = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            paramsStrength.leftMargin = (int) (progress * fDensity);
            num_tv.setLayoutParams(paramsStrength);
            num_tv.setText("速度-"+numbers);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub
        }
    };

    private void initView() {
        displaysMetrics = getResources().getDisplayMetrics();
        width = displaysMetrics.widthPixels;
        fDensity = (width - dip2px(this, 100)) / 100;
        seekBar = (SeekBar) findViewById(R.id.rgb_sb_model_speed);
        num_tv = (TextView) findViewById(R.id.rgb_tv_model_speed);
        tickSeekBar = findViewById(R.id.seekbar_listener);
    }

    /**
     * 根据手机分辨率从 px(像素) 单位 转成 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据手机分辨率从 dp 单位 转成 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
