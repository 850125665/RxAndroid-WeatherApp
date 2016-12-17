package edu.xtu.androidbase.weaher.ui.weather.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.xtu.androidbase.weaher.R;
import edu.xtu.androidbase.weaher.ui.base.BaseFragment;
import edu.xtu.androidbase.weaher.ui.base.TerminalToolBarActivity;
import edu.xtu.androidbase.weaher.ui.weather.adapter.SelectCityRecycleAdapter;
import edu.xtu.androidbase.weaher.ui.weather.domain.City;
import edu.xtu.androidbase.weaher.ui.weather.domain.CityDao;
import edu.xtu.androidbase.weaher.ui.weather.domain.Province;
import edu.xtu.androidbase.weaher.ui.weather.domain.SelectCity;
import edu.xtu.androidbase.weaher.ui.weather.domain.SelectCityDao;
import edu.xtu.androidbase.weaher.ui.weather.presenter.SelectCityPresenter;
import edu.xtu.androidbase.weaher.ui.weather.view.ISelectCityView;
import edu.xtu.androidbase.weaher.util.AppMethods;
import edu.xtu.androidbase.weaher.util.DbManager;
import edu.xtu.androidbase.weaher.util.LineDecoration;
import edu.xtu.androidbase.weaher.util.LogUtils;
import edu.xtu.androidbase.weaher.util.RxUtil.RxBus;
import edu.xtu.androidbase.weaher.util.RxUtil.RxHelp;
import edu.xtu.androidbase.weaher.util.view.LoadView;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by huilin on 2016/12/5.
 */

public class SelectSelectCityFragment extends BaseFragment implements ISelectCityView {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    private SelectCityRecycleAdapter selectCityRecycleAdapter;
    private LoadView.IOnNetListener iOnNetListener;
    private SelectCityPresenter selectCityPresenter;
    private int localFlag = 1;
    private String LOCAL_FLAG = "local_flag";
    private String PRO_ID = "pro_id";
    private Subscription subscribe;

    @Override
    protected void initDataBeforeView() {
        localFlag = data.getInt(LOCAL_FLAG, 1);
        selectCityPresenter = new SelectCityPresenter(this);

    }

    @Override
    protected void initData() {
//        getActivity().getWindow().setWindowAnimations(R.style.activity_anim);
//        getActivity().overridePendingTransition(R.anim.left_in,R.anim.left_out);
//        getActivity().
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
        subscribe = RxBus.getInstance().toObservable(City.class).subscribe(new Subscriber<City>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(City city) {
                LogUtils.d(TAG, new Date().getTime() + "");
                getActivity().finish();
            }
        });
    }

    @Override
    protected int getResourceId() {
        return R.layout.fragment_select_city;
    }

    @Override
    protected void getNet(LoadView.IOnNetListener iOnNetListener) {
        this.iOnNetListener = iOnNetListener;
        if (localFlag == SelectCityRecycleAdapter.CITY) {
            String proID = data.getString(PRO_ID);
            WhereCondition whereCondition = CityDao.Properties.ProID.eq(proID);
            selectCityPresenter.showCityListView(localFlag, whereCondition);
            toolBar.setToolTitle("城市选择");
        } else {
            WhereCondition whereCondition = new WhereCondition.StringCondition("1=1");
            selectCityPresenter.showCityListView(localFlag, whereCondition);
            toolBar.setToolTitle("省份选择");
        }
        selectCityRecycleAdapter.setOnClickListener(new SelectCityRecycleAdapter.onClickListener() {
            @Override
            public void onClickListener(SelectCityRecycleAdapter.ViewHolder viewHolder, final int position) {
                viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (localFlag == SelectCityRecycleAdapter.PROVINCE) {
                            Bundle bundle = new Bundle();
                            bundle.putInt(LOCAL_FLAG, SelectCityRecycleAdapter.CITY);
                            bundle.putString(PRO_ID, selectCityRecycleAdapter.provinces.get(position).getProSort());

                            TerminalToolBarActivity.show(mContext, SelectSelectCityFragment.class, bundle);
                        } else if (localFlag == SelectCityRecycleAdapter.CITY) {
                            final City city = selectCityRecycleAdapter.cities.get(position);
                            WhereCondition whereCondition = SelectCityDao.Properties.CityId.eq(Long.valueOf(city.getCitySort()));
                            DbManager.getInstant().mDaoSession.getSelectCityDao().queryBuilder().where(whereCondition)
                                    .rx()
                                    .unique()
                                    .compose(RxHelp.<SelectCity>onlineSchedul())
                                    .subscribe(new Subscriber<SelectCity>() {
                                        @Override
                                        public void onCompleted() {

                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            AppMethods.shwoToast(e.getMessage());
                                        }

                                        @Override
                                        public void onNext(SelectCity selectCity) {
                                            if (selectCity == null) {
                                                selectCity = new SelectCity();
                                                selectCity.setCityId(Long.valueOf(city.getCitySort()));
                                                selectCity.setCityName(city.getCityName().substring(0,city.getCityName().length()-1));
                                                selectCity.setStatus(1);
                                                DbManager.getInstant().mDaoSession.getSelectCityDao().rx().insert(selectCity).compose(RxHelp.<SelectCity>onlineSchedul())
                                                        .subscribe(new Subscriber<SelectCity>() {
                                                            @Override
                                                            public void onCompleted() {

                                                            }

                                                            @Override
                                                            public void onError(Throwable e) {
                                                                AppMethods.shwoToast(e.getMessage());
                                                            }

                                                            @Override
                                                            public void onNext(SelectCity selectCity) {
                                                                AppMethods.shwoToast("添加成功");
                                                                RxBus.getInstance().postEven(new City());
                                                            }
                                                        });

                                            } else {
                                                selectCity.setStatus(1);
                                                DbManager.getInstant().mDaoSession.getSelectCityDao()
                                                        .rx()
                                                        .update(selectCity)
                                                        .compose(RxHelp.<SelectCity>onlineSchedul())
                                                        .subscribe(new Action1<SelectCity>() {
                                                            @Override
                                                            public void call(SelectCity selectCity) {
                                                                AppMethods.shwoToast("该城市已被添加，无需再次添加");
//                                                                RxBus.getInstance().postEven(new City());
                                                            }
                                                        });
                                            }
                                        }
                                    });


                        }
                    }
                });
            }
        });
//        iOnNetListener.getState(LoadView.LoadResult.success);
    }


    @Override
    public void showCityListView(List<City> cities) {
        selectCityRecycleAdapter.setData(cities, SelectCityRecycleAdapter.CITY);
        iOnNetListener.getState(LoadView.LoadResult.success);
    }

    @Override
    public void showProvinceView(List<Province> provinces) {
        selectCityRecycleAdapter.setData(provinces, SelectCityRecycleAdapter.PROVINCE);
        iOnNetListener.getState(LoadView.LoadResult.success);
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
        if (subscribe != null && !subscribe.isUnsubscribed()) {
            LogUtils.d(TAG, "unsubscibe");
            subscribe.unsubscribe();
        }
    }
}
