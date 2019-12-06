package com.nbhope.chia.brvah;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.google.gson.reflect.TypeToken;
import com.nbhope.chia.MainActivity;
import com.nbhope.chia.R;
import com.nbhope.chia.brvah.bean.CheckBoxBean;
import com.nbhope.chia.brvah.bean.MusicBean;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: CheckBoxActivity
 * @Description: 作用描述
 * @Author: CHIA
 * @CreateDate: 2019/12/5
 */
public class CheckBoxActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView mRvView;
    private TextView mAll;
    private TextView mCancel;
    private TextView mInvert;
    private CheckAdapter checkAdapter;
    private List<CheckBoxBean> lists;
    private List<CheckBoxBean> checkedDatas;

    private List<String>listDatas=new ArrayList<>();
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
        checkAdapter = new CheckAdapter(this, lists,checkedDatas);
        mRvView.setAdapter(checkAdapter);

        //子条目监听
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

        CheckBoxBean checkBoxBean9 = new CheckBoxBean();
        checkBoxBean9.setName("麻子");
        checkBoxBean9.setAge(20);
        lists.add(checkBoxBean9);

        CheckBoxBean checkBoxBean10 = new CheckBoxBean();
        checkBoxBean10.setName("小红");
        checkBoxBean10.setAge(20);
        lists.add(checkBoxBean10);

        CheckBoxBean checkBoxBean11 = new CheckBoxBean();
        checkBoxBean11.setName("小菊");
        checkBoxBean11.setAge(20);
        lists.add(checkBoxBean11);

        CheckBoxBean checkBoxBean12 = new CheckBoxBean();
        checkBoxBean12.setName("小孟");
        checkBoxBean12.setAge(20);
        lists.add(checkBoxBean12);

        CheckBoxBean checkBoxBean13 = new CheckBoxBean();
        checkBoxBean13.setName("小刘");
        checkBoxBean13.setAge(20);
        lists.add(checkBoxBean13);

        //添加选中数据
        CheckBoxBean checkBoxBean14 = new CheckBoxBean();
        checkBoxBean14.setName("小李");
        checkBoxBean14.setAge(20);
        checkedDatas.add(checkBoxBean14);

        CheckBoxBean checkBoxBean15 = new CheckBoxBean();
        checkBoxBean15.setName("小红");
        checkBoxBean15.setAge(20);
        //checkedDatas.add(checkBoxBean15);
    }

    private void initView() {
        mRvView = (RecyclerView) findViewById(R.id.rv_view);
        mAll = (TextView) findViewById(R.id.tv_all);
        mCancel = (TextView) findViewById(R.id.tv_cancel);
        mInvert = (TextView) findViewById(R.id.tv_invert);
        mGetData = (TextView) findViewById(R.id.tv_getData);
        mAll.setOnClickListener(this);
        mCancel.setOnClickListener(this);
        mInvert.setOnClickListener(this);
        mGetData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_getData:
                String content ="";
                listDatas.clear();

                Toast.makeText(CheckBoxActivity.this,"获取我们选取的数据",Toast.LENGTH_SHORT).show();
                Log.e("TAG",mGetData.getText().toString());

                Map<Integer, Boolean> map = checkAdapter.getMap();
                for(int i =0;i<lists.size();i++){
                    if(map.get(i)){
                        listDatas.add(lists.get(i).getName());
                    }
                }

                //这里是为了测试我们的结果 ，可忽略！
                for (int j=0;j<listDatas.size();j++){
                    Log.e("TAG",listDatas.get(j));
                    content+=listDatas.get(j);
                }
                mGetData.setText(content);
                Log.e("TAG",content);
                break;

            //以下三个功能后续扩展
            case R.id.tv_all:
                Toast.makeText(CheckBoxActivity.this,"全选",Toast.LENGTH_SHORT).show();
                break;

            case R.id.tv_cancel:
                Toast.makeText(CheckBoxActivity.this,"取消全选",Toast.LENGTH_SHORT).show();
                break;

            case R.id.tv_invert:
                Toast.makeText(CheckBoxActivity.this,"反选",Toast.LENGTH_SHORT).show();
                break;

        }
    }

    //Adapter数据填充
    class CheckAdapter extends RecyclerView.Adapter<CheckAdapter.CheckViewHolder>{
        private Context mContext;
        private List<CheckBoxBean> lists;
        private List<CheckBoxBean> checkedDatasTemp;
        private List<CheckBoxBean> checkedDatas = new ArrayList();
        private HashMap<Integer,Boolean> Maps=new HashMap<Integer,Boolean>();
        private HashMap<Integer,Boolean>AllMaps=new HashMap<Integer,Boolean>();
        private List<Integer> isCheck = new ArrayList<>();

        //成员方法，初始化checkBox的状态，默认全部不选中
        public CheckAdapter(Context context, List<CheckBoxBean> lists,List<CheckBoxBean> checkedDatas){
            this.mContext=context;
            this.lists=lists;
            this.checkedDatasTemp = checkedDatas;
            initMap();
        }

        //初始化map内的数据状态，全部重置为false，即为选取状态
        private void initMap() {
            for (int i = 0; i < lists.size(); i++) {
                for (int j = 0; j < checkedDatasTemp.size(); j++){
                    if (lists.get(i).getName().equals(checkedDatasTemp.get(j).getName())){
                        Maps.put(i, true);
                        isCheck.add(i);
                    }
                }
            }
        }

        //获取最终的map存储数据
        public Map<Integer,Boolean>getMap(){
            return Maps;
        }

        //后续扩展 - 获取最终的map存储数据
        public Map<Integer,Boolean>getAllMap(){
            return AllMaps;
        }

        //点击item选中CheckBox
        public void setSelectItem(int position) {
            //对当前状态取反
            if (Maps.get(position)) {
                Maps.put(position, false);
            } else {
                Maps.put(position, true);
            }
            notifyItemChanged(position);
        }
        @Override
        public CheckAdapter.CheckViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            CheckViewHolder checkViewHolder = new CheckViewHolder(LayoutInflater.from(CheckBoxActivity.this).inflate(R.layout.item_layout, parent,false));
            return checkViewHolder;
        }

        @Override
        public void onBindViewHolder(final CheckAdapter.CheckViewHolder holder, final int position) {

            holder.mName.setText(lists.get(position).getName());
            holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Maps.put(position,isChecked);
                    if (isChecked){
                        checkedDatas.add(lists.get(position));
                        checkedItem(checkedDatas);
                    }else {
                        checkedDatas.remove(lists.get(position));
                        checkedItem(checkedDatas);
                    }
                    if (isCheck.contains(position)){
                        holder.mCheckBox.setClickable(false);
                    }else {
                        isCheck.clear();
                        isCheck.add(position);
                        holder.mCheckBox.setChecked(true);
                    }
                    Log.i("jiawei","CheckBox position 1111: " + lists.get(position).getName() + "---" + isChecked);
                }
            });

            if(Maps.get(position)==null){
                Maps.put(position,false);
            }
            //没有设置tag之前会有item重复选框出现，设置tag之后，此问题解决
            holder.mCheckBox.setChecked(Maps.get(position));

            //之后扩展使用
            AllMaps.put(position,true);
        }

        @Override
        public int getItemCount() {
            return lists ==null?0:lists.size();
        }

        class CheckViewHolder extends RecyclerView.ViewHolder {
            private  TextView mName;
            private CheckBox mCheckBox;
            private LinearLayout layout;
            public CheckViewHolder(View itemView) {
                super(itemView);
                mName = (TextView) itemView.findViewById(R.id.item_name);
                mCheckBox = (CheckBox) itemView.findViewById(R.id.item_cb);
                layout = itemView.findViewById(R.id.item_click);
            }
        }
    }

    private void checkedItem(List<CheckBoxBean> checked) {
        for (int i = 0 ;i<checked.size();i++){
            Log.i("jiawei","CheckBox position 2222: " + checked.get(i).getName());
        }
    }

}
