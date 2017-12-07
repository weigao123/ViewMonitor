package com.garfield.viewmonitor.api;

import android.support.annotation.Nullable;

/**
 * Created by gaowei on 2017/11/16.
 */

public interface ExposureCallback<T> {
    void onExposure(@Nullable T data, int position);
}
