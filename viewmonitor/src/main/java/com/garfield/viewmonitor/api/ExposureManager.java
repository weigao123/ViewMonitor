package com.garfield.viewmonitor.api;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.garfield.viewmonitor.core.RootLayout;
import com.garfield.viewmonitor.util.L;
import com.garfield.viewmonitor.util.ScreenUtil;

/**
 * Created by gaowei on 2017/12/6.
 */

public class ExposureManager {

    private static ExposureManager instance;

    public static ExposureManager getInstance() {
        if (instance == null) {
            instance = new ExposureManager();
        }
        return instance;
    }

    public void init(Application application) {
        ActivityLifecycle activityLifecycle = new ActivityLifecycle();
        application.registerActivityLifecycleCallbacks(activityLifecycle);
        ScreenUtil.init(application);
    }

    private void attachTrackerFrameLayout(Activity activity) {
        if (activity == null) {
            return;
        }
        try {
            ViewGroup container = (ViewGroup) activity.findViewById(android.R.id.content);
            if (container == null) {
                return;
            }
            if (container.getChildCount() > 0) {
                if (!(container.getChildAt(0) instanceof RootLayout)) {
                    RootLayout rootLayout = new RootLayout(activity);
                    while (container.getChildCount() > 0) {
                        View view = container.getChildAt(0);
                        container.removeViewAt(0);
                        rootLayout.addView(view, view.getLayoutParams());
                    }
                    container.addView(rootLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                }
            }
        } catch (Exception e) {
            L.e(e.toString());
        }
    }

    private void detachTrackerFrameLayout(Activity activity) {
        if (activity == null) {
            return;
        }
        try {
            ViewGroup container = (ViewGroup) activity.findViewById(android.R.id.content);
            if (container == null) {
                return;
            }
            if (container.getChildAt(0) instanceof RootLayout) {
                container.removeViewAt(0);
            }
        } catch (Exception e) {
            L.e(e.toString());
        }
    }

    private class ActivityLifecycle implements Application.ActivityLifecycleCallbacks {
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {
            attachTrackerFrameLayout(activity);
        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            detachTrackerFrameLayout(activity);
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }
    }
}
