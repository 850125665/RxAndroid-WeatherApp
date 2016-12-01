package edu.xtu.androidbase.weaher.ui.weather.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.xtu.androidbase.weaher.R;
import edu.xtu.androidbase.weaher.ui.base.BaseFragment;
import edu.xtu.androidbase.weaher.ui.weather.adapter.CityWeatherRecycleAdapter;
import edu.xtu.androidbase.weaher.ui.weather.adapter.WeatherDetailRecycleAdapter;
import edu.xtu.androidbase.weaher.ui.weather.domain.Weather;
import edu.xtu.androidbase.weaher.util.GridDecoration;
import edu.xtu.androidbase.weaher.util.LineDecoration;
import edu.xtu.androidbase.weaher.util.view.LoadView;

/**
 * Created by huilin on 2016/11/30.
 */

public class WeatherDetailFragment extends BaseFragment {

    @Bind(R.id.tv_city)
    TextView tvCity;
    @Bind(R.id.img_weather)
    ImageView imgWeather;
    @Bind(R.id.tv_desc)
    TextView tvDesc;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    private WeatherDetailRecycleAdapter weatherDetailRecycleAdapter;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new LineDecoration(mContext));
        weatherDetailRecycleAdapter = new WeatherDetailRecycleAdapter();
        recyclerView.setAdapter(weatherDetailRecycleAdapter);
        showToolBar();
        toolBar.setToolTitle("北京");
    }

    @Override
    protected int getResourceId() {
        return R.layout.fragment_weather_detail;
    }

    @Override
    protected void getNet(LoadView.IOnNetListener iOnNetListener) {
        iOnNetListener.getState(LoadView.LoadResult.success);
    }



}
