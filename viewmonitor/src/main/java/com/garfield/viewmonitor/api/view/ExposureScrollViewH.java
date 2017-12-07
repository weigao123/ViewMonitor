package com.garfield.viewmonitor.api.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaowei on 2017/12/7.
 */

public class ExposureScrollViewH extends HorizontalScrollView {

    private List<ExposureScrollView.OnScrollChangeListener> mListener = new ArrayList<>();

    public ExposureScrollViewH(Context context) {
        super(context);
    }

    public ExposureScrollViewH(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExposureScrollViewH(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        for (ExposureScrollView.OnScrollChangeListener listener : mListener) {
            listener.onScrollChanged(l, t, oldl, oldt);
        }
    }

    public void addOnScrollChangeListener(ExposureScrollView.OnScrollChangeListener listener) {
        mListener.add(listener);
    }

    public void removeOnScrollChangeListener(ExposureScrollView.OnScrollChangeListener listener) {
        mListener.remove(listener);
    }
}
