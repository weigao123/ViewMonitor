package com.garfield.app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.garfield.viewmonitor.api.view.ExposureFrameLayout;

/**
 * Created by gaowei on 2017/12/6.
 */

public class MyFragment extends Fragment {

    private String[] myDataset = new String[100];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.layout_fragment1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView(view);
    }

    protected void initView(View view) {

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);

        ExposureFrameLayout layout = (ExposureFrameLayout) view.findViewById(R.id.clickview);
        layout.bindData("-click", 0, null, null);
        TextView textView = (TextView) view.findViewById(R.id.click_me);
        textView.setOnClickListener(clickListener);

        LinearLayout horizontalScrollView = (LinearLayout) view.findViewById(R.id.horizontal_scrollview);
        int size = (int) getResources().getDisplayMetrics().density * 100;
        for (int i = 0; i < 10; i++) {
            TextView item = new TextView(getContext());
            item.setTextColor(Color.BLACK);
            item.setGravity(Gravity.CENTER);
            item.setText("item " + i);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
            params.gravity = Gravity.CENTER;
            horizontalScrollView.addView(item, params);
        }

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        for (int i = 0; i < myDataset.length; i++) {
            myDataset[i] = "item " + i;
        }
        RecyclerView.Adapter adapter = new RecyclerAdapter(myDataset);
        recyclerView.setAdapter(adapter);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.click_me:
                    Toast.makeText(getContext(), "check logcat", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

}
