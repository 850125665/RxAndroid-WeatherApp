package edu.xtu.androidbase.weaher.ui.weather.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.xtu.androidbase.weaher.R;
import edu.xtu.androidbase.weaher.ui.base.BaseFragment;
import edu.xtu.androidbase.weaher.ui.weather.adapter.CityRecycleAdapter;
import edu.xtu.androidbase.weaher.ui.weather.domain.City;
import edu.xtu.androidbase.weaher.ui.weather.view.ICityView;
import edu.xtu.androidbase.weaher.util.AppMethods;
import edu.xtu.androidbase.weaher.util.LineDecoration;
import edu.xtu.androidbase.weaher.util.view.LoadView;

/**
 * Created by huilin on 2016/11/12.
 */
public class CityFragment extends BaseFragment {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    private CityRecycleAdapter cityRecycleAdapter;
    LoadView.IOnNetListener iOnNetListener;

    @Override
    protected void initDataBeforeView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new LineDecoration(mContext));
        cityRecycleAdapter = new CityRecycleAdapter();
        recyclerView.setAdapter(cityRecycleAdapter);
    }

    @Override
    protected int getResourceId() {
        return R.layout.fragment_city;
    }

    @Override
    protected void getNet(LoadView.IOnNetListener iOnNetListener) {
        this.iOnNetListener = iOnNetListener;
        iOnNetListener.getState(LoadView.LoadResult.success);
    }



}
