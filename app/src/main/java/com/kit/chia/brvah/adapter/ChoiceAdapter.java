package com.kit.chia.brvah.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.kit.chia.R;
import com.kit.chia.brvah.bean.ChoiceBean;

import java.util.ArrayList;
import java.util.List;

public class ChoiceAdapter extends RecyclerView.Adapter<ChoiceAdapter.ChoiceVH> {

    private List<ChoiceBean> mDatas;
    private Context mContext;
    private LayoutInflater mInflater;

    private int mSelectedPos = -1;//实现单选  方法二，变量保存当前选中的position

    private RecyclerView mRv;//实现单选方法三： RecyclerView另一种定向刷新方法：

    private List<ChoiceBean> checkedData = new ArrayList<>();

    private onItemCheckListener itemCheckListener;

    public ChoiceAdapter(List<ChoiceBean> datas,Context context,RecyclerView rv){
        this.mDatas = datas;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mRv = rv;

        //实现单选方法二：设置数据集时，找到默认选中的pos
        for (int i = 0 ; i < mDatas.size() ; i++){
            if (mDatas.get(i).isSelected()){
                mSelectedPos = i;
            }
        }
    }

    @NonNull
    @Override
    public ChoiceVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChoiceVH(mInflater.inflate(R.layout.choice_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ChoiceVH holder, final int position) {
        Log.d("jiawei", "onBindViewHolder() called with: holder = [" + holder + "], position = [" + position + "]");
        holder.ivSelect.setSelected(mDatas.get(position).isSelected());
        holder.tvCoupon.setText(mDatas.get(position).getName());
        holder.cbSelect.setChecked(mDatas.get(position).isSelected());
        
        holder.llSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0;i<mDatas.size();i++){
                    if (i != position){
                        mDatas.get(i).setSelected(false);
                    }
                }
                mDatas.get(position).setSelected(!mDatas.get(position).isSelected());
                //isChecked = mDatas.get(position).isSelected();
                notifyDataSetChanged();
                if (mDatas.get(position).isSelected()){
                    // 选中
                    //Toast.makeText(mContext, "选中："+mDatas.get(position).getName(), Toast.LENGTH_SHORT).show();
                    checkedData.add(mDatas.get(position));
                }else {
                    // 取消
                    //Toast.makeText(mContext, "取消："+mDatas.get(position).getName(), Toast.LENGTH_SHORT).show();
                    checkedData.remove(mDatas.get(position));
                }
                itemCheckListener.itemCheck(checkedData);
            }
        });
        holder.cbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //Toast.makeText(mContext, "点击了：checkbox", Toast.LENGTH_SHORT).show();
            }
        });
        holder.ivSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //实现单选，第一种方法，十分简单， Lv Rv通用,因为它们都有notifyDataSetChanged()方法
                // 每次点击时，先将所有的selected设为false，并且将当前点击的item 设为true， 刷新整个视图
                /*for (ChoiceBean bean : mDatas){
                    bean.setSelected(false);
                }*/
                for (int i = 0;i<mDatas.size();i++){
                    if (i != position){
                        mDatas.get(i).setSelected(false);
                    }
                }
                mDatas.get(position).setSelected(!mDatas.get(position).isSelected());
                //isChecked = mDatas.get(position).isSelected();
                notifyDataSetChanged();
                //实现单选方法二： notifyItemChanged() 定向刷新两个视图
                //如果勾选的不是已经勾选状态的Item
                /*if (mSelectedPos != position){
                    //先取消上一个勾选状态
                    mDatas.get(mSelectedPos).setSelected(false);
                    notifyItemChanged(mSelectedPos);
                    //设置新的勾选状态
                    mSelectedPos = position;
                    mDatas.get(mSelectedPos).setSelected(true);
                    notifyItemChanged(mSelectedPos);
                }*/
                //实现单选方法三： RecyclerView另一种定向刷新方法：不会有白光一闪动画 也不会重复onBindVIewHolder
                /*ChoiceVH choiceVH = (ChoiceVH) mRv.findViewHolderForLayoutPosition(mSelectedPos);
                if (choiceVH != null){//还在屏幕里
                    holder.ivSelect.setSelected(false);
                }else {//一些极端情况，holder被缓存在Recycler的cacheView里，
                    //此时拿不到ViewHolder，但是也不会回调onBindViewHolder方法。所以add一个异常处理
                    notifyItemChanged(mSelectedPos);
                }
                if (mSelectedPos != position){
                    mDatas.get(mSelectedPos).setSelected(false);
                    //设置新Item的勾选状态
                    mSelectedPos = position;
                    mDatas.get(mSelectedPos).setSelected(true);
                    holder.ivSelect.setSelected(true);
                }*/

                //实现单选方法四：
                /*if (mSelectedPos != position){
                    //先取消上个item的勾选状态
                    if (mSelectedPos != -1){
                        mDatas.get(mSelectedPos).setSelected(false);
                    }
                    //传递一个payload
                    Bundle payloadOld = new Bundle();
                    payloadOld.putBoolean("KEY_BOOLEAN", false);
                    notifyItemChanged(mSelectedPos, payloadOld);
                    //设置新Item的勾选状态
                    mSelectedPos = position;
                    if (mDatas.get(mSelectedPos).isSelected()){
                        mDatas.get(mSelectedPos).setSelected(false);
                    }else {
                        mDatas.get(mSelectedPos).setSelected(true);
                    }
                    Bundle payloadNew = new Bundle();
                    payloadNew.putBoolean("KEY_BOOLEAN", true);
                    notifyItemChanged(mSelectedPos, payloadNew);
                }*/

            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull ChoiceVH holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        Log.d("jiawei", "onBindViewHolder() called with: holder = [" + holder + "], position = [" + position + "], payloads = [" + payloads + "]");
        if (payloads.isEmpty()){
            onBindViewHolder(holder,position);
        }else {
            Bundle payload = (Bundle) payloads.get(0);
            if (payload.containsKey("KEY_BOOLEAN")){
                boolean aBoolean = payload.getBoolean("KEY_BOOLEAN");
                holder.ivSelect.setSelected(aBoolean);
            }
        }
    }

    @Override
    public int getItemCount() {
        return null != mDatas ? mDatas.size() : 0;
    }

    public static class ChoiceVH extends RecyclerView.ViewHolder{

        private ImageView ivSelect;
        private TextView tvCoupon;
        private CheckBox cbSelect;
        private LinearLayout llSelect;

        public ChoiceVH(View itemView){
            super(itemView);
            ivSelect = (ImageView) itemView.findViewById(R.id.ivSelect);
            tvCoupon = (TextView) itemView.findViewById(R.id.tvCoupon);
            cbSelect = itemView.findViewById(R.id.cb_select);
            llSelect = itemView.findViewById(R.id.ll_select);
        }
    }

    public void setOnItemCheckListener(onItemCheckListener listener){
        this.itemCheckListener = listener;
    }

    public interface onItemCheckListener{
        void itemCheck(List<ChoiceBean> bean);
    }
}
