package com.godframe.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.godframe.R;


/**
 * Created by HOHO on 2017/5/8.
 */

public class LoadProgressDialog extends Dialog {

    private Activity act;
    private LinearLayout mView;
    private TextView text_tv;
    private ProgressBar parogressbar;

    /**
     * 初始化
     *
     * @param context
     */
    public LoadProgressDialog(Context context) {
        super(context, R.style.progress_dialog);
        this.act = (Activity) context;
        mView = (LinearLayout) act.getLayoutInflater().inflate(R.layout.progress_loaddialog, null, false);
        text_tv = mView.findViewById(R.id.text_tv);
        parogressbar = mView.findViewById(R.id.parogressbar);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

    }

    public void setText(String text) {
        text_tv.setText(text);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        this. setCancelable(true);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setContentView(mView);
        window.setGravity( Gravity.CENTER); // 此处可以设置dialog显示的位置
        window.setLayout(dip2px(getContext(),100),dip2px(getContext(),100));
    }
    public  int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
