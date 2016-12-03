package edu.xtu.androidbase.weaher.ui.weather.fragment;

import android.app.ActivityOptions;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.SharedElementCallback;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    SharedElementCallback sharedElementCallback = new SharedElementCallback() {
        @Override
        public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
            super.onMapSharedElements(names, sharedElements);
        }
    };


    @Override
    protected void initData() {

    }

    @Override
    protected void initView(final View view) {
        ButterKnife.bind(this, view);
        setExitSharedElementCallback(sharedElementCallback);
        final GridLayoutManager layoutManager = new GridLayoutManager(mContext, 2, LinearLayoutManager.VERTICAL, false);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 0 ? layoutManager.getSpanCount() : 1;
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridDecoration(mContext));
        cityWeatherRecycleAdapter = new CityWeatherRecycleAdapter();
        cityWeatherRecycleAdapter.setDatas(new ArrayList<Weather>());
        recyclerView.setAdapter(cityWeatherRecycleAdapter);
        cityWeatherRecycleAdapter.setOnClickListener(new CityWeatherRecycleAdapter.OnClickListener() {
            @Override
            public void OnClickItem(RecyclerView.ViewHolder holder, int position) {
                holder = (CityWeatherRecycleAdapter.GridViewHolder) holder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    ImageView imageView = ((CityWeatherRecycleAdapter.GridViewHolder) holder).contentImg;
                    View statusBar = getActivity().findViewById(android.R.id.statusBarBackground);
                    View navigationBar = getActivity().findViewById(android.R.id.navigationBarBackground);

                    List<Pair<? extends View, String>> pairs = new ArrayList<Pair<? extends View, String>>();
//                    pairs.add(Pair.create(statusBar, Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME));
//                    pairs.add(Pair.create(navigationBar,Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME));
                    pairs.add(Pair.create(imageView, String.valueOf(position)));
                    Bundle bundle = new Bundle();
                    bundle.putString("transition", String.valueOf(position));
                    ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(getActivity(), pairs.toArray(new Pair[pairs.size()]));

//                    imageView.setTransitionName(String.valueOf(position));
//                    imageView.setTag(String.valueOf(position));
//                    bundle.putBundle(TerminalToolBarActivity.SCENE_TRANSITION, ActivityOptions.makeSceneTransitionAnimation(getActivity(),
//                            imageView,String.valueOf(position)).toBundle());
                    bundle.putBundle(TerminalToolBarActivity.SCENE_TRANSITION, activityOptions.toBundle());
                    TerminalToolBarActivity.show(mContext, WeatherDetailFragment.class, bundle);
                } else {
                    TerminalToolBarActivity.show(mContext, WeatherDetailFragment.class, new Bundle());
                }
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
