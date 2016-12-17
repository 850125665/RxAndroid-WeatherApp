package edu.xtu.androidbase.weaher.util;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by huilin on 2016/12/16.
 */

public class BaseAdapter extends RecyclerView.Adapter {


    private boolean isOpenLoadMore = false;
    private int COMMON_TYPE = 0;
    private int FOOT_TYPE = 1;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return isOpenLoadMore&&position>=getItemCount()-1?FOOT_TYPE:COMMON_TYPE;
    }



}
