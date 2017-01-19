package edu.xtu.androidbase.weaher.presenter;

import java.util.ArrayList;
import java.util.List;

import edu.xtu.androidbase.weaher.domain.Weather;
import edu.xtu.androidbase.weaher.model.MainModel;
import edu.xtu.androidbase.weaher.model.request.MainRequest;
import edu.xtu.androidbase.weaher.ui.weather.view.IMainView;
import edu.xtu.androidbase.weaher.util.DbManager;
import edu.xtu.androidbase.weaher.util.LogUtils;
import rx.Subscriber;

/**
 * Created by huilin on 2016/11/12.
 */
public class MainPresenter {

    private String TAG = this.getClass().getSimpleName();
    private MainModel mainModel;
    private IMainView iMainView;
    private MainRequest mainRequest;

    public MainPresenter(IMainView iMainView) {
        this.iMainView = iMainView;
        mainRequest = new MainRequest();
        mainModel = new MainModel();
    }

    public void showWeatherList(){
        mainRequest.setSelectCities(DbManager.getInstant().mDaoSession.getSelectCityDao().loadAll());
        final List<Weather> weathers = new ArrayList<>();
        mainModel.showWeatherList(mainRequest)
                .subscribe(new Subscriber<Weather>() {
                    @Override
                    public void onCompleted() {
                        iMainView.showWeatherList(weathers);
                        iMainView.success();
                    }

                    @Override
                    public void onError(Throwable e) {
                        iMainView.error(e.getMessage());
                    }

                    @Override
                    public void onNext(Weather weather) {
                        LogUtils.d(TAG,weather.toString());
                        weathers.add(weather);
                    }
                });
    }

}
