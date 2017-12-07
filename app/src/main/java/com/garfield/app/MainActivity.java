/*
 * Copyright （C）2010-2017 Alibaba Group Holding Limited
 */

package com.garfield.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by gaowei on 2017/12/7.
 */

public class MainActivity extends AppCompatActivity {

    private MyFragment mFragment1;
    private MyFragment mFragment2;
    private int now = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragment1 = new MyFragment();
        mFragment2 = new MyFragment();
        setFragment();

        findViewById(R.id.activity_button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });

        findViewById(R.id.activity_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment(now);
            }
        });

    }

    private void setFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.activity_main, mFragment1).add(R.id.activity_main, mFragment2).show(mFragment1).hide(mFragment2);
        ft.commit();
    }

    private void switchFragment(int i) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (i == 1) {
            now = 2;
            ft.show(mFragment2).hide(mFragment1);
        } else {
            now = 1;
            ft.show(mFragment1).hide(mFragment2);
        }
        ft.commit();
    }

}
