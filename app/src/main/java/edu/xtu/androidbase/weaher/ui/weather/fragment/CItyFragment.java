package edu.xtu.androidbase.weaher.ui.weather.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.xtu.androidbase.weaher.R;
import edu.xtu.androidbase.weaher.ui.base.PageBaseFragment;
import edu.xtu.androidbase.weaher.util.BaseAdapter;
import edu.xtu.androidbase.weaher.util.TouchListener;
import edu.xtu.androidbase.weaher.adapter.CityRecycleAdapter;
import edu.xtu.androidbase.weaher.domain.City;
import edu.xtu.androidbase.weaher.domain.SelectCity;
import edu.xtu.androidbase.weaher.presenter.CityPresenter;
import edu.xtu.androidbase.weaher.ui.weather.view.ICityView;
import edu.xtu.androidbase.weaher.util.AppMethods;
import edu.xtu.androidbase.weaher.util.DbManager;
import edu.xtu.androidbase.weaher.util.LineDecoration;
import edu.xtu.androidbase.weaher.util.RxUtil.RxBus;
import edu.xtu.androidbase.weaher.util.view.LoadView;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by huilin on 2016/11/12.
 */
public class CityFragment extends PageBaseFragment implements ICityView{

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
        super.initData();
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
                       isFresh = true;
                        cityPresenter.showCityList(0,PAGE_NUM);
                    }
                });
    }

    @Override
    protected RecyclerView getRecycleView() {
        return recyclerView;
    }

    @Override
    protected BaseAdapter getBaseAdapter() {
        return cityRecycleAdapter;
    }

    @Override
    protected void loadMoreNet() {
        cityPresenter.showCityList((cityRecycleAdapter.getCount()-1)/ PAGE_NUM+1, PAGE_NUM);
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new LineDecoration(mContext));
        cityRecycleAdapter = new CityRecycleAdapter();
        cityRecycleAdapter.setOpenLoadMore(true);
        cityRecycleAdapter.setLoadMoreListener(this);
        recyclerView.setAdapter(cityRecycleAdapter);
        TouchListener touchListener = new TouchListener(recyclerView, cityRecycleAdapter.cities, cityRecycleAdapter);
        touchListener.setOnClickListener(new TouchListener.OnClickListener() {
            @Override
            public <T extends RecyclerView.ViewHolder> void onItemClickListener(T t, int position) {

            }

            @Override
            public <T extends RecyclerView.ViewHolder> void onItemLongClickListener(T t, int position) {
            }
        });
        recyclerView.addOnItemTouchListener(touchListener);
        touchListener.dragItem( new TouchListener.DragItemListener() {
            @Override
            public void drag() {

            }

            @Override
            public void swipe(int position) {
                SelectCity selectCity = cityRecycleAdapter.cities.get(position);
                DbManager.getInstant().mDaoSession.getSelectCityDao().delete(selectCity);
            }
        });

    }

    @Override
    protected int getResourceId() {
        return R.layout.fragment_city;
    }


    @Override
    protected void getNet(LoadView.IOnNetListener iOnNetListener) {
        super.getNet(iOnNetListener);
        this.iOnNetListener = iOnNetListener;
        cityPresenter.showCityList(0, PAGE_NUM);
    }

    @Override
    public void showListCity(List<SelectCity> selectCities) {
        if (isFreshData()) {
            if(selectCities.isEmpty()){
                iOnNetListener.getState(LoadView.LoadResult.empty);
            }else{
                iOnNetListener.getState(LoadView.LoadResult.success);
            }
            cityRecycleAdapter.setDatas(selectCities);
        } else {

            cityRecycleAdapter.addDatas(selectCities);
            iOnNetListener.getState(LoadView.LoadResult.success);
        }
    }

    @Override
    public void error(String msg) {
        AppMethods.shwoToast(msg);
        iOnNetListener.getState(LoadView.LoadResult.error);
        netCallBack(false);
    }

    @Override
    public void success() {
//        iOnNetListener.getState(LoadView.LoadResult.success);
        netCallBack(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (rxBus != null && !rxBus.isUnsubscribed()) {
            rxBus.unsubscribe();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
