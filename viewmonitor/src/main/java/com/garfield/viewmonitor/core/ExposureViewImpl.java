package com.garfield.viewmonitor.core;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewParent;

import com.garfield.viewmonitor.api.ExposureCallback;
import com.garfield.viewmonitor.util.L;
import com.garfield.viewmonitor.util.ScreenUtil;

import static android.view.View.VISIBLE;

/**
 * Created by gaowei on 2017/9/19.
 */

public class ExposureViewImpl<T> implements ExposureView<T> {

    private final static int HIDDEN = 0;
    private final static int EXPOSURE = 1;
    private boolean mViewVisible = false;   // Fragment切换不可见，而实际状态可见
    private int mViewState = HIDDEN;        // 是否曝光

    private boolean mIsLayoutEnd;

    private Rect mRegionRect = new Rect(0, 0, ScreenUtil.screenWidth, ScreenUtil.screenHeight);

    private int[] mLocation = new int[2];

    private T mT;
    private int mPosition = -1;
    private ExposureCallback<T> mCallback;

    private @NonNull View mView;
    private RootLayout mRootLayout;

    @SuppressWarnings("all")
    public ExposureViewImpl(@NonNull View view) {
        if (view == null || !(view instanceof ExposureView))
            throw new IllegalArgumentException();
        mView = view;
    }

    @Override
    public void bindListData(@Nullable T t, int position, @Nullable ExposureCallback<T> callback) {
        mT = t;
        mPosition = position;
        mCallback = callback;
        if (mViewVisible) {
            onExposure(t, position);
        }
    }

    @Override
    public void bindData(@Nullable T t, int position, @Nullable Rect rect, @Nullable ExposureCallback<T> callback) {
        mT = t;
        mPosition = position;
        mCallback = callback;
        if (rect != null) {
            mRegionRect = rect;
        }
        postCalcExposure();
    }

    @Override
    public void onVisibilityChanged(@NonNull View changedView, int visibility) {
        View view = mView;
        mViewVisible = true;
        while (view != null) {
            if (view.getVisibility() != VISIBLE) {
                mViewVisible = false;
                break;
            }
            ViewParent parent = view.getParent();
            view = parent != null && parent instanceof View ? (View) parent : null;
        }
        if (mViewVisible) {
            // switch fragment may not be able to get view's size immediately.
            postCalcExposure();
        } else {
            mViewState = HIDDEN;
        }
    }

    private void postCalcExposure() {
        mView.post(new Runnable() {
            @Override
            public void run() {
                mIsLayoutEnd = true;
                calcExposure();
            }
        });
    }

    private void calcExposure() {
        if (mIsLayoutEnd && mRegionRect != null && mViewVisible) {
            mView.getLocationOnScreen(mLocation);
            int viewWidth = mView.getMeasuredWidth();
            int viewHeight = mView.getMeasuredHeight();
            if (viewWidth == 0 || viewHeight == 0) return;
            if (mRegionRect.contains(mLocation[0], mLocation[1], mLocation[0] + viewWidth, mLocation[1] + viewHeight)) {
                if (mViewState == HIDDEN) {
                    mViewState = EXPOSURE;
                    onExposure(mT, mPosition);
                }
            } else {
                mViewState = HIDDEN;
            }
        }
    }

    @Override
    public void onScrollChange() {
        calcExposure();
    }

    @Override @SuppressWarnings("unchecked")
    public void onExposure(@Nullable T data, int position) {
        if (mCallback != null) {
            mCallback.onExposure(data, position);
        } else {
            ((ExposureView) mView).onExposure(data, position);
        }
        L.d("data: " + (data == null ? "null" : data) + "    position: " + position);
    }

    @Override
    public void onAttachedToWindow() {
        mRootLayout = ViewHook.getRootLayout(mView);
        if (mRootLayout != null) {
            mRootLayout.hookParent(mView);
            mRootLayout.register((ExposureView) mView);
        }
        postCalcExposure();
    }

    @Override
    public void onDetachedFromWindow() {
        if (mRootLayout != null) {
            mRootLayout.unRegister((ExposureView) mView);
        }
    }

    @Override
    public RootLayout getRootLayout() {
        return mRootLayout;
    }

}
