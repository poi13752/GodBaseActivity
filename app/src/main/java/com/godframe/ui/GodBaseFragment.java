package com.godframe.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.godframe.listener.GodOnClickListener;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public abstract class GodBaseFragment extends Fragment implements View.OnClickListener {
    protected View mView;
    protected Context mContext;
    protected Intent mIntent;
    private GodOnClickListener mGodOnClickListener;
    private SparseArray<View> views;
    public String title;

    protected abstract int onCreateLayout();

    protected abstract void init();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(onCreateLayout(), container, false);
        init();
        return mView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        views = new SparseArray<>();
        mContext = getContext();
        mIntent = new Intent();
    }

    protected void skip(Class activity) {
        mIntent.setClass(getContext(), activity);
        startActivity(mIntent);
    }

    protected void skipResult(Class activity, int requestCode) {
        mIntent.setClass(getContext(), activity);
        startActivityForResult(mIntent, requestCode);
    }



    protected void toast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //    public ImageView setGlideImagView(int id, String url, int errorid) {
//        ImageView imageView = getView(id);
//        Glide.with(this).load(url).error(errorid).into(imageView);
//        return imageView;
//    }


    protected void setClickable(int id, boolean clickable) {
        View view = getView(id);
        view.setClickable(clickable);
    }


    protected TextView setText(int viewId, String text) {
        TextView textView = getView(viewId);
        textView.setText(text);
        return textView;
    }

    protected TextView setTextColor(int viewId, int colorId) {
        TextView textView = getView(viewId);
        textView.setTextColor(colorId);
        return textView;
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

    protected View setBackgroundColor(int viewId, int colorId) {
        View view = getView(viewId);
        view.setBackgroundColor(colorId);
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

    protected View setAlphaAnimation(int viewId, float fromAlpha, float toAlpha, long duration) {
        View view = getView(viewId);
        Animation animation = new AlphaAnimation(fromAlpha, toAlpha);
        animation.setDuration(duration);
        view.startAnimation(animation);
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


    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null && mView != null) {
            view = mView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public TextView getTextView(int viewId) {
        TextView view = (TextView) views.get(viewId);
        if (view == null && mView != null) {
            view = (TextView) mView.findViewById(viewId);
            views.put(viewId, view);
        }
        return view;
    }

    public EditText getEditText(int viewId) {
        EditText view = (EditText) views.get(viewId);
        if (view == null && mView != null) {
            view = (EditText) mView.findViewById(viewId);
            views.put(viewId, view);
        }
        return view;
    }

    public ImageView getImageView(int viewId) {
        ImageView view = (ImageView) views.get(viewId);
        if (view == null && mView != null) {
            view = (ImageView) mView.findViewById(viewId);
            views.put(viewId, view);
        }
        return view;
    }


    @Override
    public void onClick(View v) {
        if (mGodOnClickListener != null) {
            mGodOnClickListener.OnClickListener(v);
        }
    }


}
