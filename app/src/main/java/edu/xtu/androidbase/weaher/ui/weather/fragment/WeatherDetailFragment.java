package edu.xtu.androidbase.weaher.ui.weather.fragment;

import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.xtu.androidbase.weaher.R;
import edu.xtu.androidbase.weaher.ui.base.BaseFragment;
import edu.xtu.androidbase.weaher.ui.weather.adapter.CityWeatherRecycleAdapter;
import edu.xtu.androidbase.weaher.ui.weather.adapter.WeatherDetailRecycleAdapter;
import edu.xtu.androidbase.weaher.ui.weather.domain.Weather;
import edu.xtu.androidbase.weaher.util.LineDecoration;
import edu.xtu.androidbase.weaher.util.LogUtils;
import edu.xtu.androidbase.weaher.util.TouchListener;
import edu.xtu.androidbase.weaher.util.view.CommonDialog;
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
    private Weather weather;
    @Override
    protected void initDataBeforeView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        setRefreshEnable(false);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new LineDecoration(mContext));
        weatherDetailRecycleAdapter = new WeatherDetailRecycleAdapter();
        recyclerView.setAdapter(weatherDetailRecycleAdapter);
        showToolBar();


        String tag = data.getString("transition");
        weather = (Weather) data.getSerializable("weather");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().postponeEnterTransition();
            imgWeather.setTransitionName(tag);
            getActivity().startPostponedEnterTransition();
        }
        toolBar.setToolTitle(weather.basic.city);
        tvCity.setText(weather.basic.city);
        imgWeather.setImageDrawable(CityWeatherRecycleAdapter.getImgDrawable(mContext,Integer.valueOf(weather.now.cond.code)));
        tvDesc.setText(weather.suggestion.sport.txt);
        weatherDetailRecycleAdapter.setDatas(weather.dailyForecast);
    }

    @Override
    protected int getResourceId() {
        return R.layout.fragment_weather_detail;
    }

    @Override
    protected void getNet(LoadView.IOnNetListener iOnNetListener) {
        iOnNetListener.getState(LoadView.LoadResult.success);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getActivity().startPostponedEnterTransition();
//        }

    }


}
