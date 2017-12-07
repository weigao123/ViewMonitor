package com.garfield.viewmonitor.api.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaowei on 2017/12/7.
 */

public class ExposureScrollView extends ScrollView {

    private List<OnScrollChangeListener> mListener = new ArrayList<>();

    public ExposureScrollView(Context context) {
        super(context);
    }

    public ExposureScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExposureScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        for (OnScrollChangeListener listener : mListener) {
            listener.onScrollChanged(l, t, oldl, oldt);
        }
    }

    public void addOnScrollChangeListener(OnScrollChangeListener listener) {
        mListener.add(listener);
    }

    public void removeOnScrollChangeListener(OnScrollChangeListener listener) {
        mListener.remove(listener);
    }

    public interface OnScrollChangeListener {
        void onScrollChanged(int l, int t, int oldl, int oldt);
    }
}
