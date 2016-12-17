package edu.xtu.androidbase.weaher.ui.weather.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.xtu.androidbase.weaher.R;
import edu.xtu.androidbase.weaher.ui.base.BaseFragment;
import edu.xtu.androidbase.weaher.ui.test.TouchListener;
import edu.xtu.androidbase.weaher.ui.weather.adapter.CityRecycleAdapter;
import edu.xtu.androidbase.weaher.ui.weather.domain.City;
import edu.xtu.androidbase.weaher.ui.weather.domain.SelectCity;
import edu.xtu.androidbase.weaher.ui.weather.presenter.CityPresenter;
import edu.xtu.androidbase.weaher.ui.weather.view.ICityView;
import edu.xtu.androidbase.weaher.util.AppMethods;
import edu.xtu.androidbase.weaher.util.LineDecoration;
import edu.xtu.androidbase.weaher.util.RxUtil.RxBus;
import edu.xtu.androidbase.weaher.util.view.LoadView;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by huilin on 2016/11/12.
 */
public class CityFragment extends BaseFragment implements ICityView {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    private CityRecycleAdapter cityRecycleAdapter;
    LoadView.IOnNetListener iOnNetListener;
    private CityPresenter cityPresenter;
    Subscription rxBus;

    @Override
    protected void initDataBeforeView() {
        cityPresenter = new CityPresenter(this);
    }

    @Override
    protected void initData() {
        rxBus = RxBus.getInstance().toObservable(City.class)
                .subscribe(new Subscriber<City>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(City city) {
                        cityPresenter.showCityList();
                    }
                });
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new LineDecoration(mContext));
        cityRecycleAdapter = new CityRecycleAdapter();
        recyclerView.setAdapter(cityRecycleAdapter);
        TouchListener touchListener = new TouchListener(recyclerView,cityRecycleAdapter.cities,cityRecycleAdapter);
        touchListener.setOnClickListener(new TouchListener.OnClickListener() {
            @Override
            public <T extends RecyclerView.ViewHolder> void onItemClickListener(T t, int position) {
                AppMethods.shwoToast("单点击"+position);
            }

            @Override
            public <T extends RecyclerView.ViewHolder> void onItemLongClickListener(T t, int position) {
                AppMethods.shwoToast("长按"+position);
            }
        });
        recyclerView.addOnItemTouchListener(touchListener);
        touchListener.dragItem(swipeRefreshLayout);
//        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//
//
//            }
//        });

    }

    @Override
    protected int getResourceId() {
        return R.layout.fragment_city;
    }

    @Override
    protected void getNet(LoadView.IOnNetListener iOnNetListener) {
        this.iOnNetListener = iOnNetListener;
        cityPresenter.showCityList();
//        iOnNetListener.getState(LoadView.LoadResult.success);
    }


    @Override
    public void showListCity(List<SelectCity> selectCities) {
        cityRecycleAdapter.setDatas(selectCities);
    }

    @Override
    public void error(String msg) {
        AppMethods.shwoToast(msg);
        iOnNetListener.getState(LoadView.LoadResult.error);
    }

    @Override
    public void success() {
        iOnNetListener.getState(LoadView.LoadResult.success);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (rxBus != null && !rxBus.isUnsubscribed()) {
            rxBus.unsubscribe();
        }
    }
}
