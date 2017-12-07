package com.garfield.viewmonitor.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by gaowei3 on 2017/12/6.
 */
public class ScreenUtil {

    public static int screenWidth;
    public static int screenHeight;

    public static void init(Context context) {
        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
    }
}
