package edu.xtu.androidbase.weaher.ui.weather.presenter;

import java.util.List;

import edu.xtu.androidbase.weaher.ui.weather.domain.SelectCity;
import edu.xtu.androidbase.weaher.ui.weather.model.CityModel;
import edu.xtu.androidbase.weaher.ui.weather.view.ICityView;
import edu.xtu.androidbase.weaher.util.LogUtils;
import rx.Subscriber;

/**
 * Created by huilin on 2016/12/14.
 */

public class CityPresenter {
    private String TAG = getClass().getSimpleName();
    private CityModel cityModel;
    private ICityView iCityView;

    public CityPresenter(ICityView iCityView) {
        this.iCityView = iCityView;
        cityModel = new CityModel();
    }

    public void showCityList(){
        cityModel.showListCity()
                .subscribe(new Subscriber<List<SelectCity>>() {
                    @Override
                    public void onCompleted() {
                        iCityView.success();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(TAG,e.getMessage());
                        iCityView.error(e.getMessage());
                    }

                    @Override
                    public void onNext(List<SelectCity> selectCities) {
                        iCityView.showListCity(selectCities);
                    }
                });
    }
}
