package com.nbhope.chia.dialog.style;

import android.support.annotation.LayoutRes;
import com.nbhope.chia.dialog.widget.BaseDialog;
import com.nbhope.chia.dialog.widget.ViewHolder;

/**
 * @ClassName: CommonDialog
 * @Description: 作用描述
 * @Author: CHIA
 * @CreateDate: 2019/12/19
 */
public class CommonDialog extends BaseDialog {

    private ViewConvertListener convertListener;

    public static CommonDialog newInstance() {
        CommonDialog dialog = new CommonDialog();
        return dialog;
    }

    /**
     * 设置Dialog布局
     *
     * @param layoutId
     * @return
     */
    public CommonDialog setLayoutId(@LayoutRes int layoutId) {
        this.mLayoutResId = layoutId;
        return this;
    }

    @Override
    public int setUpLayoutId() {
        return mLayoutResId;
    }

    @Override
    public void convertView(ViewHolder holder, BaseDialog dialog) {
        if (convertListener != null) {
            convertListener.convertView(holder, dialog);
        }
    }

    public CommonDialog setConvertListener(ViewConvertListener convertListener) {
        this.convertListener = convertListener;
        return this;
    }

    public interface ViewConvertListener{
        void convertView(ViewHolder holder, BaseDialog dialog);
    }
}
