package edu.xtu.androidbase.weaher.ui.weather.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.xtu.androidbase.weaher.R;
import edu.xtu.androidbase.weaher.ui.base.BaseFragment;
import edu.xtu.androidbase.weaher.ui.weather.adapter.RecycleAdapter;
import edu.xtu.androidbase.weaher.ui.weather.domain.Weather;
import edu.xtu.androidbase.weaher.util.AppMethods;
import edu.xtu.androidbase.weaher.util.GridDecoration;
import edu.xtu.androidbase.weaher.util.view.LoadView;

/**
 * Created by huilin on 2016/11/12.
 */
public class MainFragment extends BaseFragment {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    private RecycleAdapter recycleAdapter;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this,view);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mContext,2, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridDecoration(mContext));
        recycleAdapter = new RecycleAdapter();
        recycleAdapter.setDatas(new ArrayList<Weather>());
        recyclerView.setAdapter(recycleAdapter);
        recycleAdapter.setOnClickListener(new RecycleAdapter.OnClickListener() {
            @Override
            public void OnClickItem() {
                AppMethods.shwoToast("点击了");
            }
        });

    }

    @Override
    protected int getResourceId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void getNet(LoadView.IOnNetListener iOnNetListener) {
            iOnNetListener.getState(LoadView.LoadResult.success);
    }




}
