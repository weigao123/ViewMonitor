/*
 * Copyright （C）2010-2017 Alibaba Group Holding Limited
 */

package com.garfield.app;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.garfield.viewmonitor.api.view.ExposureTextView;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static final String TAG = "RecyclerAdapter";
    
    private String[] mDataset;

    View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
        }
    };

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ExposureTextView mTextView;

        public ViewHolder(ExposureTextView v) {
            super(v);
            mTextView = v;
        }
    }

    public RecyclerAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ExposureTextView v = (ExposureTextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        v.setOnClickListener(clickListener);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.bindRecyclerData("-RecyclerView", position, null);
        holder.mTextView.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}