package com.garfield.viewmonitor.core;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaowei on 2017/12/6.
 */

public class RootLayout extends FrameLayout {

    private List<ExposureView> mExposureViews = new ArrayList<>();

    private ViewHook mViewHook = new ViewHook();

    private static final float MOVE_LIMIT = 20;
    private float mOriX, mOriY;

    public RootLayout(@NonNull Context context) {
        super(context);
    }

    public RootLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RootLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void register(ExposureView view) {
        mExposureViews.add(view);
    }

    public void unRegister(ExposureView view) {
        mExposureViews.remove(view);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mOriX = ev.getX();
                mOriY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if ((Math.abs(ev.getX() - mOriX) > MOVE_LIMIT) || (Math.abs(ev.getY() - mOriY) > MOVE_LIMIT)) {
                    notifyScrollChange();
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    public void notifyScrollChange() {
        for (ExposureView view : mExposureViews) {
            view.onScrollChange();
        }
    }

    public void hookParent(View view) {
        mViewHook.hookParent(view);
    }
}
