package com.nbhope.chia.brvah;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import com.nbhope.chia.R;
import com.nbhope.chia.brvah.adapter.CheckBoxAdapter;
import com.nbhope.chia.brvah.bean.CheckBoxBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: BaseQuickActivity
 * @Description: 作用描述
 * @Author: CHIA
 * @CreateDate: 2019/12/5
 */
public class BaseQuickActivity extends AppCompatActivity {

    private RecyclerView mRvView;
    private TextView mAll;
    private TextView mCancel;
    private TextView mInvert;
    private CheckBoxAdapter checkAdapter;
    private List<CheckBoxBean> lists;
    private List<CheckBoxBean> checkedDatas;

    private List<String> listDatas=new ArrayList<>();
    private TextView mGetData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);

        lists = new ArrayList<>();
        checkedDatas = new ArrayList<>();
        initData();

        initView();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRvView.setLayoutManager(linearLayoutManager);
        checkAdapter = new CheckBoxAdapter(R.layout.item_layout, lists,checkedDatas);
        mRvView.setAdapter(checkAdapter);
    }

    private void initView() {
        mRvView = (RecyclerView) findViewById(R.id.rv_view);
        mAll = (TextView) findViewById(R.id.tv_all);
        mCancel = (TextView) findViewById(R.id.tv_cancel);
        mInvert = (TextView) findViewById(R.id.tv_invert);
        mGetData = (TextView) findViewById(R.id.tv_getData);
    }

    private void initData() {
        CheckBoxBean checkBoxBean1 = new CheckBoxBean();
        checkBoxBean1.setName("张三");
        checkBoxBean1.setAge(20);
        lists.add(checkBoxBean1);

        CheckBoxBean checkBoxBean2 = new CheckBoxBean();
        checkBoxBean2.setName("李四");
        checkBoxBean2.setAge(20);
        lists.add(checkBoxBean2);

        CheckBoxBean checkBoxBean3 = new CheckBoxBean();
        checkBoxBean3.setName("王二");
        checkBoxBean3.setAge(20);
        lists.add(checkBoxBean3);

        CheckBoxBean checkBoxBean4 = new CheckBoxBean();
        checkBoxBean4.setName("麻子");
        checkBoxBean4.setAge(20);
        lists.add(checkBoxBean4);

        CheckBoxBean checkBoxBean5 = new CheckBoxBean();
        checkBoxBean5.setName("小红");
        checkBoxBean5.setAge(20);
        lists.add(checkBoxBean5);

        CheckBoxBean checkBoxBean6 = new CheckBoxBean();
        checkBoxBean6.setName("小菊");
        checkBoxBean6.setAge(20);
        lists.add(checkBoxBean6);

        CheckBoxBean checkBoxBean7 = new CheckBoxBean();
        checkBoxBean7.setName("小李");
        checkBoxBean7.setAge(20);
        lists.add(checkBoxBean7);

        CheckBoxBean checkBoxBean8 = new CheckBoxBean();
        checkBoxBean8.setName("小刘");
        checkBoxBean8.setAge(20);
        lists.add(checkBoxBean8);

        //添加选中数据
        CheckBoxBean checkBoxBean9 = new CheckBoxBean();
        checkBoxBean9.setName("小李");
        checkBoxBean9.setAge(20);
        checkedDatas.add(checkBoxBean9);

        CheckBoxBean checkBoxBean10 = new CheckBoxBean();
        checkBoxBean10.setName("小红");
        checkBoxBean10.setAge(20);
        checkedDatas.add(checkBoxBean10);
    }

}
