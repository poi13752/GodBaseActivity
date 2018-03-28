package com.godframe.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.zhy.autolayout.utils.AutoUtils;


/**
 * Created by Administrator on 2017/10/18 0018.
 */

public abstract class GodBasePopupWindow extends PopupWindow {
    protected View mView;
    protected Context mContext;

    public GodBasePopupWindow(int layoutId, Context context) {
        this.mContext = context;
        mView = LayoutInflater.from(context).inflate(layoutId, null, false);
        AutoUtils.autoSize(mView);
        setContentView(mView);
        init();
    }

    protected abstract void init();

}
