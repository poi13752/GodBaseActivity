package com.godframe.ui;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.godframe.R;
import com.godframe.listener.GodOnClickListener;
import com.godframe.listener.GodOnLongClickListener;

/**
 * Created by Administrator on 2017/5/16 0016.
 */

public abstract class GodLeftHandBaseActivity extends GodBaseActivity implements View.OnLongClickListener, View.OnClickListener {
    private static final String TAG = "GodLeftHandBaseActivity";
    private GodOnClickListener mGodOnClickListener;
    private GodOnLongClickListener mGodOnLongClickListener;
    private SparseArray<View> views = new SparseArray<>();
    protected TextView basetitle_tv;
    protected ImageView baseback_img;
    protected View basetop_view, godbase_view, basestatus_view;

    @Override
    protected void onGodCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_base);
        resetStatusViewHeight();
        initTopView();
        init(savedInstanceState);
    }

    protected abstract void init(Bundle savedInstanceState);

    //初始化头布局
    protected void initTopView() {
        View view = LayoutInflater.from(this).inflate(setContentLayoutId(), null, false);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);
        FrameLayout content_view = (FrameLayout) findViewById(R.id.content_view);
        content_view.addView(view);
        basetop_view = findViewById(R.id.basetop_view);
        baseback_img = (ImageView) findViewById(R.id.baseback_img);
        basetitle_tv = (TextView) findViewById(R.id.basetitle_tv);
        godbase_view = findViewById(R.id.godbase_view);
        findViewById(R.id.baseback_img).setOnClickListener(this);
    }


    //设置头布局状态栏背景颜色
    protected void setTopViewBackgroundColor(int colorId) {
        basetop_view.setBackgroundColor(colorId);
        basestatus_view.setBackgroundColor(colorId);
    }

    //设置状态栏颜色
    protected void setStatusBackgroundColor(int colorId) {
        basestatus_view.setBackgroundColor(colorId);
    }

    //将状态栏高度赋给basestatus_view
    private void resetStatusViewHeight() {
        basestatus_view = findViewById(R.id.basestatus_view);
        int statusheight = getStatusBarHeight();
        if (statusheight != -1) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusheight);
            basestatus_view.setLayoutParams(params);
        }
    }

    //是否显示状态栏，默认显示
    protected void showStatusView(boolean show) {
        if (!show) basestatus_view.setVisibility(View.GONE);
        else basestatus_view.setVisibility(View.VISIBLE);
    }

    //设置标题
    protected void setTitle(String title) {
        basetitle_tv.setText(title);
    }

    protected abstract int setContentLayoutId();

    //隐藏头布局
    public void hideTopView() {
        basetop_view.setVisibility(View.GONE);
        basestatus_view.setVisibility(View.GONE);
    }

    //隐藏返回键
    public void hideBackView() {
        baseback_img.setVisibility(View.GONE);
    }

    protected void setClickable(int viewId, boolean clickable) {
        View view = getView(viewId);
        view.setClickable(clickable);
    }


    protected TextView setText(int viewId, String text) {
        TextView textView = getView(viewId);
        textView.setText(text);
        return textView;
    }

    protected TextView setText(int viewId, int res_id) {
        TextView textView = getView(viewId);
        textView.setText(getString(res_id));
        return textView;
    }

    protected TextView setTextColor(int viewId, int colorId) {
        TextView textView = getView(viewId);
        textView.setTextColor(colorId);
        return textView;
    }

    protected View setBackgroundColor(int viewId, int colorId) {
        View view = getView(viewId);
        view.setBackgroundColor(colorId);
        return view;
    }

    protected RatingBar setRating(int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return view;
    }

    protected RatingBar setRating(int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return view;
    }

    protected ImageView setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return view;
    }

    protected ImageView setImageDrawable(int viewId, int imgid) {
        ImageView view = getView(viewId);
        view.setImageResource(imgid);
        return view;
    }

    protected ImageView setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return view;
    }


    protected View setBackgroundResource(int viewId, int backgroundResource) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundResource);
        return view;
    }

    protected View setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return view;
    }

    protected View setVisibility(int viewId, int visible) {
        View view = getView(viewId);
        switch (visible) {
            case View.VISIBLE:
                view.setVisibility(View.VISIBLE);
                break;
            case View.INVISIBLE:
                view.setVisibility(View.INVISIBLE);
                break;
            case View.GONE:
                view.setVisibility(View.GONE);
                break;
            default:
                break;
        }
        return view;
    }

    protected ImageView setImageResource(int viewId, int imageResId) {
        ImageView view = getView(viewId);
        view.setImageResource(imageResId);
        return view;
    }

    protected ProgressBar setProgress(int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return view;
    }

    protected View setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return view;
    }

    protected View setTag(int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return view;
    }

    protected View setChecked(int viewId, boolean checked) {
        View view = getView(viewId);
        if (view instanceof CompoundButton) {
            ((CompoundButton) view).setChecked(checked);
        } else if (view instanceof CheckedTextView) {
            ((CheckedTextView) view).setChecked(checked);
        }
        return view;
    }


    protected View setRecyclerViewAdapter(int viewId, RecyclerView.Adapter adapter) {
        View view = getView(viewId);
        if (view instanceof RecyclerView) {
            ((RecyclerView) view).setAdapter(adapter);
        }
        return (RecyclerView) view;
    }

    protected View setListViewAdapter(int viewId, BaseAdapter adapter) {
        View view = getView(viewId);
        if (view instanceof ListView) {
            ((ListView) view).setAdapter(adapter);
        }
        return (ListView) view;
    }

    protected RecyclerView setRecyclerViewLayoutManager(int viewId, RecyclerView.LayoutManager layoutManager) {
        View view = getView(viewId);
        if (view instanceof RecyclerView) {
            ((RecyclerView) view).setLayoutManager(layoutManager);
        }
        return (RecyclerView) view;
    }

    protected View scrollTo(int viewId, int x, int y) {
        View view = getView(viewId);
        view.scrollTo(x, y);
        return view;
    }

    protected void setOnClickListener(GodOnClickListener godOnClickListener, int... viewIds) {
        this.mGodOnClickListener = godOnClickListener;
        for (int viewId : viewIds) {
            View view = getView(viewId);
            view.setOnClickListener(this);
        }
    }

    protected void setOnLongClickListener(final GodOnLongClickListener mGodOnLongClickListener, int... viewIds) {
        this.mGodOnLongClickListener = mGodOnLongClickListener;
        for (int viewId : viewIds) {
            View view = getView(viewId);
            view.setOnLongClickListener(this);

        }
    }

    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public TextView getTextView(int viewId) {
        TextView view = (TextView) views.get(viewId);
        if (view == null) {
            view = (TextView) findViewById(viewId);
            views.put(viewId, view);
        }
        return view;
    }

    public EditText getEditText(int viewId) {
        EditText view = (EditText) views.get(viewId);
        if (view == null) {
            view = (EditText) findViewById(viewId);
            views.put(viewId, view);
        }
        return view;
    }

    public ImageView getImageView(int viewId) {
        ImageView view = (ImageView) views.get(viewId);
        if (view == null) {
            view = (ImageView) findViewById(viewId);
            views.put(viewId, view);
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        if (mGodOnClickListener != null) {
            mGodOnClickListener.OnClickListener(v);
        }
        if (v.getId() == R.id.baseback_img) {
            finish();
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (mGodOnLongClickListener != null) {
            mGodOnLongClickListener.OnLongClickListener(v);
        }
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        views = null;
    }
}
