package com.garfield.viewmonitor.util;

import android.util.Log;

/**
 * Created by gaowei on 2017/12/6.
 */

public class L {

    private static final String TAG = "view_monitor";

    public static void d(Object object) {
        Log.d(TAG, object.toString());
    }

    public static void e(Object object) {
        Log.e(TAG, object.toString());
    }
}
