package com.kit.chia.brvah;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.kit.chia.R;
import com.kit.chia.brvah.adapter.SelectAdapter;

import java.util.ArrayList;
import java.util.List;

public class SelectedActivity extends AppCompatActivity {

    private SelectAdapter myAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<String> mDatas;
    private List<String> defaultData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected);

        mDatas = new ArrayList<>();
        //初始化控件
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        //在加载数据之前配置
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //创建一个适配器
        myAdapter = new SelectAdapter(initDatas(),initDefault());
        recyclerView.setAdapter(myAdapter);
    }

    private List<String> initDefault(){
        defaultData.add("测试数据：" + 1);
        defaultData.add("测试数据：" + 3);
        return defaultData;
    }

    private List<String> initDatas(){
        for (int i = 0 ; i < 30 ; i++){
            mDatas.add("测试数据：" + i);
        }
        return mDatas;
    }
    public void btnAll(View view) {
        myAdapter.All();
    }

    public void btnner(View view) {
        myAdapter.neverall();
    }
}
