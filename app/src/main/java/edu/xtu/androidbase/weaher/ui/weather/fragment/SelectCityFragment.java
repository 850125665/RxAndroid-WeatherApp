package edu.xtu.androidbase.weaher.ui.weather.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.xtu.androidbase.weaher.R;
import edu.xtu.androidbase.weaher.ui.base.BaseFragment;
import edu.xtu.androidbase.weaher.ui.weather.adapter.SelectCityRecycleAdapter;
import edu.xtu.androidbase.weaher.util.LineDecoration;
import edu.xtu.androidbase.weaher.util.view.LoadView;

/**
 * Created by huilin on 2016/12/5.
 */

public class SelectCityFragment extends BaseFragment {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    private SelectCityRecycleAdapter selectCityRecycleAdapter;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        showToolBar();
        toolBar.setToolTitle("城市选择");
        selectCityRecycleAdapter = new SelectCityRecycleAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new LineDecoration(mContext));
        recyclerView.setAdapter(selectCityRecycleAdapter);
    }

    @Override
    protected int getResourceId() {
        return R.layout.fragment_select_city;
    }

    @Override
    protected void getNet(LoadView.IOnNetListener iOnNetListener) {
        iOnNetListener.getState(LoadView.LoadResult.success);
    }


}
