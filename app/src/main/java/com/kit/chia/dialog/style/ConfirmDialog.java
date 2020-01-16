package com.kit.chia.dialog.style;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;
import com.kit.chia.R;
import com.kit.chia.dialog.widget.BaseDialog;
import com.kit.chia.dialog.widget.ViewHolder;

/**
 * @ClassName: ConfirmDialog
 * @Description: 作用描述
 * @Author: CHIA
 * @CreateDate: 2019/12/19
 */
public class ConfirmDialog extends BaseDialog {

    private String type;

    public static ConfirmDialog newInstance(String type) {
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        ConfirmDialog dialog = new ConfirmDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        type = bundle.getString("type");
    }

    @Override
    public int setUpLayoutId() {
        return R.layout.config_dialog;
    }

    @Override
    public void convertView(ViewHolder holder,final BaseDialog dialog) {
        if ("1".equals(type)) {
            holder.setText(R.id.title, "提示");
            holder.setText(R.id.tv_dialog_des, "您已支付成功!");
        } else if ("2".equals(type)) {
            holder.setText(R.id.title, "警告");
            holder.setText(R.id.tv_dialog_des, "您的帐号已被冻结!");
        }

        holder.setOnClickListener(R.id.cancel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        holder.setOnClickListener(R.id.confirm, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(getContext(), "确定", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
