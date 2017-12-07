package com.garfield.viewmonitor.api.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.garfield.viewmonitor.api.ExposureCallback;
import com.garfield.viewmonitor.core.ExposureView;
import com.garfield.viewmonitor.core.ExposureViewImpl;
import com.garfield.viewmonitor.core.RootLayout;

/**
 * Created by gaowei on 2017/9/19.
 */

public class ExposureLinearLayout<T> extends LinearLayout implements ExposureView<T> {

    private ExposureView<T> mExposureView;

    public ExposureLinearLayout(Context context) {
        this(context, null);
    }

    public ExposureLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExposureLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mExposureView = new ExposureViewImpl<>(this);
    }

    @Override
    public void bindRecyclerData(@Nullable T t, int position, @Nullable ExposureCallback<T> callback) {
        mExposureView.bindRecyclerData(t, position, callback);
    }

    @Override
    public void bindData(@Nullable T t, int position, @Nullable Rect rect, @Nullable ExposureCallback<T> callback) {
        mExposureView.bindData(t, position, rect, callback);
    }

    @Override
    public void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        mExposureView.onVisibilityChanged(changedView, visibility);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mExposureView.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mExposureView.onDetachedFromWindow();
    }

    @Override
    public void onScrollChange() {
        mExposureView.onScrollChange();
    }

    @Override
    public RootLayout getRootLayout() {
        return mExposureView.getRootLayout();
    }

    @Override
    public void onExposure(@Nullable T data, int position) {

    }
}
