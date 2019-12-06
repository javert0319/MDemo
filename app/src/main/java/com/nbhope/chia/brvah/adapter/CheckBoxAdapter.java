package com.nbhope.chia.brvah.adapter;

import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nbhope.chia.R;
import com.nbhope.chia.brvah.bean.CheckBoxBean;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: CheckBoxAdapter
 * @Description: 作用描述
 * @Author: CHIA
 * @CreateDate: 2019/12/5
 */
public class CheckBoxAdapter extends BaseQuickAdapter<CheckBoxBean,BaseViewHolder> {

    private List<CheckBoxBean> checkedDatas;
    private List<CheckBoxBean> lists;
    private HashMap<Integer,Boolean> Maps=new HashMap<Integer,Boolean>();

    public CheckBoxAdapter(int layoutResId, @Nullable List<CheckBoxBean> data, @Nullable List<CheckBoxBean> checked) {
        super(layoutResId, data);
        this.lists=data;
        this.checkedDatas = checkedDatas;
        initMap();
    }

    @Override
    protected void convert(BaseViewHolder helper, CheckBoxBean item) {
        helper.setText(R.id.item_name,item.getName());
        CheckBox checkBox = helper.getView(R.id.item_cb);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            }
        });
    }

    //初始化map内的数据状态，全部重置为false，即为选取状态
    private void initMap() {
        for (int i = 0; i < lists.size(); i++) {
            for (int j = 0; j < checkedDatas.size(); j++){
                if (lists.get(i).getName().equals(checkedDatas.get(j).getName())){
                    Maps.put(i, true);
                }
            }
        }
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
}
