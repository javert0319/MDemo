package com.kit.chia.brvah.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kit.chia.R;
import com.kit.chia.brvah.bean.MtestEntity;

import java.util.List;

/**
 * @ClassName: MultiItemAdapter
 * @Description: 作用描述
 * @Author: CHIA
 * @CreateDate: 2019/12/3
 */
public class MultiItemAdapter extends BaseMultiItemQuickAdapter<MtestEntity, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     *
     *             未能查询到对应房屋发票信息
     */
    public MultiItemAdapter(List<MtestEntity> data) {
        super(data);
        addItemType(1, R.layout.bravh_item_one);
        addItemType(2, R.layout.bravh_item_two);
        addItemType(3, R.layout.bravh_item_two);
    }

    @Override
    protected void convert(BaseViewHolder helper, MtestEntity item) {
        switch (helper.getItemViewType()) {
            case 1:
                helper.setText(R.id.tv_bravh_txt,item.getName());
                break;
            default:
                helper.setText(R.id.tv_bravh_txt_two,item.getName());
                break;
        }
    }
}
