package edu.xtu.androidbase.weaher.ui.weather.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.xtu.androidbase.weaher.R;
import edu.xtu.androidbase.weaher.ui.base.BaseFragment;
import edu.xtu.androidbase.weaher.ui.base.TerminalToolBarActivity;
import edu.xtu.androidbase.weaher.ui.weather.adapter.CityWeatherRecycleAdapter;
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

    private CityWeatherRecycleAdapter cityWeatherRecycleAdapter;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this,view);
        final GridLayoutManager layoutManager = new GridLayoutManager(mContext,2, LinearLayoutManager.VERTICAL,false);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position ==0?layoutManager.getSpanCount():1;
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridDecoration(mContext));
        cityWeatherRecycleAdapter = new CityWeatherRecycleAdapter();
        cityWeatherRecycleAdapter.setDatas(new ArrayList<Weather>());
        recyclerView.setAdapter(cityWeatherRecycleAdapter);
        cityWeatherRecycleAdapter.setOnClickListener(new CityWeatherRecycleAdapter.OnClickListener() {
            @Override
            public void OnClickItem() {
                TerminalToolBarActivity.show(mContext,WeatherDetailFragment.class,new Bundle());
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
