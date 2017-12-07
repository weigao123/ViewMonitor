package com.garfield.app;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.garfield.viewmonitor.api.view.ExposureTextView;

/**
 * Created by gaowei on 2017/12/7.
 */

public class ViewPagerAdapter extends PagerAdapter {

    public Object instantiateItem(ViewGroup container, int position) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(container.getContext()).inflate(R.layout.layout_child, null);
        ExposureTextView textView = (ExposureTextView) linearLayout.findViewById(R.id.pager_textview);
        textView.bindData("-ViewPager", position, null, null);
        textView.setText(position + "");
        switch (position) {
            case 0:
                linearLayout.setBackgroundColor(Color.parseColor("#2196F3"));
                break;
            case 1:
                linearLayout.setBackgroundColor(Color.parseColor("#673AB7"));
                break;
            case 2:
                linearLayout.setBackgroundColor(Color.parseColor("#009688"));
                break;
            case 3:
                linearLayout.setBackgroundColor(Color.parseColor("#607D8B"));
                break;
            case 4:
                linearLayout.setBackgroundColor(Color.parseColor("#F44336"));
                break;
        }
        container.addView(linearLayout);
        return linearLayout;
    }
    public void destroyItem(ViewGroup container, int position, Object object) {
        LinearLayout view = (LinearLayout) object;
        container.removeView(view);
    }
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }
    public int getCount() {
        return 5;
    }
}
