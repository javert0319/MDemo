package com.kit.chia.brvah.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kit.chia.R;
import com.kit.chia.brvah.bean.DimmerBean;

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
        TextView view = helper.getView(R.id.tv_dimmer_name);
        view.setSelected(true);
        view.setText(item.getDimmerName());
        //helper.setText(R.id.tv_dimmer_name,item.getDimmerName());
        ImageView dimmerIcon = helper.getView(R.id.iv_dimmer_icon);
        if (item.isSub()){
            dimmerIcon.setVisibility(View.VISIBLE);
        }else {
            dimmerIcon.setVisibility(View.GONE);
        }
        if (item.isSub()&&item.getActionName() != null){
            Log.i("jiawei","DimmerActivity onActivityResult convert: "+ item.getDimmerName()+"---" + item.getActionName());
            TextView tvAction = helper.getView(R.id.tv_dimmer_action);
            tvAction.setVisibility(View.VISIBLE);
            tvAction.setText(item.getActionName());
        }
    }
}
