package com.godframe.ui;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.godframe.utils.ActivityManagement;
import com.zhy.autolayout.AutoLayoutActivity;


/**
 * Created by Administrator on 2017/5/16 0016.
 */

public abstract class GodBaseActivity extends AutoLayoutActivity {
    protected Intent mIntent;
    protected LoadProgressDialog mLoadProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIntent = new Intent();
        onGodCreate(savedInstanceState);
        setTranslucentStatus();
        ActivityManagement.getInstance().addActivity(this);
    }

    //判断当前设备版本号是否为4.4以上，如果是，则通过调用setTranslucentStatus让状态栏变透明
    protected void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
    }

    protected abstract void onGodCreate(Bundle savedInstanceState);

    public void toast(String text) {
        if (text != null) {
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }
    }

    public void showProgress() {
        if (mLoadProgressDialog == null) mLoadProgressDialog = new LoadProgressDialog(this);
        if (mLoadProgressDialog.isShowing()) return;
        mLoadProgressDialog.show();
    }

    public void showProgress(String text) {
        if (mLoadProgressDialog == null) mLoadProgressDialog = new LoadProgressDialog(this);
        if (mLoadProgressDialog.isShowing()) return;
        mLoadProgressDialog.setText(text);
        mLoadProgressDialog.show();
    }

    public void dismissProgress() {
        if (mLoadProgressDialog == null) return;
        if (mLoadProgressDialog.isShowing())
            mLoadProgressDialog.dismiss();
    }


    protected void skipActivity(Class cls, int ResultCode) {
        mIntent.setClass(this, cls);
        startActivityForResult(mIntent, ResultCode);
    }

    public void skipActivity(Class cls) {
        mIntent.setClass(this, cls);
        startActivity(mIntent);
    }

    protected void skipActivityFinish(Class cls) {
        mIntent.setClass(this, cls);
        startActivity(mIntent);
        finish();
    }

    protected void skipActivityFinish(Class cls, int ResultCode) {
        mIntent.setClass(this, cls);
        startActivityForResult(mIntent, ResultCode);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManagement.getInstance().removeActivity(this);
    }


    /**
     * 获取当前设备状态栏高度
     *
     * @return
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 设置状态栏透明
     *
     * @param on
     */
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}



