package com.godframe.listener;

import android.view.animation.Animation;

/**
 * Created by Administrator on 2017/5/17 0017.
 */

public interface GodAnimationListener {
    void onAnimationStart(Animation animation);
    void onAnimationEnd(Animation animation);
    void onAnimationRepeat(Animation animation);

}
