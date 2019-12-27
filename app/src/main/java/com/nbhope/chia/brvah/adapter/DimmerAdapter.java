package com.nbhope.chia.brvah.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nbhope.chia.R;
import com.nbhope.chia.brvah.bean.DimmerBean;

import java.util.List;

/**
 * @ClassName: DimmerAdapter
 * @Description: 作用描述
 * @Author: CHIA
 * @CreateDate: 2019/12/20
 */
public class DimmerAdapter extends BaseQuickAdapter<DimmerBean,BaseViewHolder> {

    private List<DimmerBean> lists;

    public DimmerAdapter(int layoutResId, @Nullable List<DimmerBean> data) {
        super(layoutResId, data);
        this.lists = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, DimmerBean item) {
        helper.setText(R.id.tv_dimmer_name,item.getDimmerName());
        Log.i("jiawei","DimmerActivity onActivityResult convert: "+item.getActionName());
        if (item.isSub()){
            ImageView dimmerIcon = helper.getView(R.id.iv_dimmer_icon);
            dimmerIcon.setVisibility(View.VISIBLE);
        }
        if (item.getActionName() != null){
            TextView tvAction = helper.getView(R.id.tv_dimmer_action);
            tvAction.setVisibility(View.VISIBLE);
            tvAction.setText(item.getActionName());
        }
    }
}
