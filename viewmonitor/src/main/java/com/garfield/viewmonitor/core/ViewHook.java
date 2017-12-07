package com.garfield.viewmonitor.core;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewParent;

import com.garfield.viewmonitor.api.view.ExposureScrollView;
import com.garfield.viewmonitor.api.view.ExposureScrollViewH;

/**
 * Created by gaowei on 2017/12/7.
 */

class ViewHook {

    private ViewPagerListener mViewPagerListener;

    private ScrollChangeListener mScrollChangeListener;

    static RootLayout getRootLayout(View view) {
        ViewParent viewParent = view.getParent();
        while (viewParent != null) {
            if (viewParent instanceof RootLayout) {
                return (RootLayout) viewParent;
            }
            viewParent = viewParent.getParent();
        }
        return null;
    }

    void hookParent(View view) {
        ViewParent viewParent = view.getParent();
        while (viewParent != null) {
            if (viewParent instanceof ViewPager) {
                RootLayout rootLayout = ((ExposureView) view).getRootLayout();
                if (mViewPagerListener == null) {
                    mViewPagerListener = new ViewPagerListener(rootLayout);
                }
                ((ViewPager) viewParent).addOnPageChangeListener(mViewPagerListener);
            }
            if (viewParent instanceof ExposureScrollView) {
                RootLayout rootLayout = ((ExposureView) view).getRootLayout();
                if (mScrollChangeListener == null) {
                    mScrollChangeListener = new ScrollChangeListener(rootLayout);
                }
                ((ExposureScrollView) viewParent).addOnScrollChangeListener(mScrollChangeListener);
            }
            if (viewParent instanceof ExposureScrollViewH) {
                RootLayout rootLayout = ((ExposureView) view).getRootLayout();
                if (mScrollChangeListener == null) {
                    mScrollChangeListener = new ScrollChangeListener(rootLayout);
                }
                ((ExposureScrollViewH) viewParent).addOnScrollChangeListener(mScrollChangeListener);
            }
            viewParent = viewParent.getParent();
        }
    }

    private static class ViewPagerListener implements ViewPager.OnPageChangeListener {

        private RootLayout mRootLayout;

        ViewPagerListener(RootLayout rootLayout) {
            mRootLayout = rootLayout;
        }

        @Override
        public void onPageScrolled(int i, float v, int i1) {
            mRootLayout.notifyScrollChange();
        }

        @Override
        public void onPageSelected(int i) {

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    }

    private static class ScrollChangeListener implements ExposureScrollView.OnScrollChangeListener {

        private RootLayout mRootLayout;

        ScrollChangeListener(RootLayout rootLayout) {
            mRootLayout = rootLayout;
        }

        @Override
        public void onScrollChanged(int l, int t, int oldl, int oldt) {
            mRootLayout.notifyScrollChange();
        }
    }

}
