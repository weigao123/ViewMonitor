package com.garfield.viewmonitor.core;

import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.view.View;

import com.garfield.viewmonitor.api.ExposureCallback;

/**
 * Created by gaowei on 2017/9/19.
 */

public interface ExposureView<T> {

    void bindListData(T t, int position, ExposureCallback<T> callback);

    void bindData(T t, int position, Rect rect, ExposureCallback<T> callback);

    void onScrollChange();

    void onVisibilityChanged(View changedView, int visibility);

    void onExposure(@Nullable T data, int position);

    void onAttachedToWindow();

    void onDetachedFromWindow();

    RootLayout getRootLayout();
}
