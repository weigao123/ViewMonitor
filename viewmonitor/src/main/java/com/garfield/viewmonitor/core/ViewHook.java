package com.garfield.viewmonitor.core;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewParent;
import android.widget.AbsListView;

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

    static boolean isRecyclerView(View view) {
        ViewParent viewParent = view.getParent();
        while (viewParent != null) {
            if (viewParent instanceof RecyclerView || viewParent instanceof AbsListView) {
                return true;
            }
            viewParent = viewParent.getParent();
        }
        return false;
    }

    void hookParent(View view) {
        ViewParent viewParent = view.getParent();
        while (viewParent != null) {
            if (viewParent instanceof ViewPager) {
                RootLayout rootLayout = ((ExposureView) view).getRootLayout();
                if (mViewPagerListener == null) {
                    mViewPagerListener = new ViewPagerListener(rootLayout);
                }
                ((ViewPager) viewParent).removeOnPageChangeListener(mViewPagerListener);
                ((ViewPager) viewParent).addOnPageChangeListener(mViewPagerListener);
            }
            if (viewParent instanceof ExposureScrollView) {
                RootLayout rootLayout = ((ExposureView) view).getRootLayout();
                if (mScrollChangeListener == null) {
                    mScrollChangeListener = new ScrollChangeListener(rootLayout);
                }
                ((ExposureScrollView) viewParent).removeOnScrollChangeListener(mScrollChangeListener);
                ((ExposureScrollView) viewParent).addOnScrollChangeListener(mScrollChangeListener);
            }
            if (viewParent instanceof ExposureScrollViewH) {
                RootLayout rootLayout = ((ExposureView) view).getRootLayout();
                if (mScrollChangeListener == null) {
                    mScrollChangeListener = new ScrollChangeListener(rootLayout);
                }
                ((ExposureScrollViewH) viewParent).removeOnScrollChangeListener(mScrollChangeListener);
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

        }

        @Override
        public void onPageSelected(int i) {

        }

        @Override
        public void onPageScrollStateChanged(int i) {
            mRootLayout.notifyScrollChange();
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
