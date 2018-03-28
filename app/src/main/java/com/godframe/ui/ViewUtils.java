package com.godframe.ui;

import com.godframe.utils.ActivityManagement;

/**
 * Created by St on 2018/3/28.
 */

public class ViewUtils {
    private static ViewUtils instance;

    private ViewUtils() {
    }

    public static synchronized ViewUtils getInstance() {
        if (instance == null) {
            instance = new ViewUtils();
        }
        return instance;
    }

    public static void replaceTopView() {


    }


}
