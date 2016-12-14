package edu.xtu.androidbase.weaher.ui.weather.presenter;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

import edu.xtu.androidbase.weaher.ui.weather.adapter.SelectCityRecycleAdapter;
import edu.xtu.androidbase.weaher.ui.weather.domain.City;
import edu.xtu.androidbase.weaher.ui.weather.domain.CityDao;
import edu.xtu.androidbase.weaher.ui.weather.domain.Province;
import edu.xtu.androidbase.weaher.ui.weather.model.CityModelImpl;
import edu.xtu.androidbase.weaher.ui.weather.model.ICityModel;
import edu.xtu.androidbase.weaher.ui.weather.model.request.CityRequest;
import edu.xtu.androidbase.weaher.ui.weather.model.request.ProvinceRequest;
import edu.xtu.androidbase.weaher.ui.weather.model.usercase.CityUseCase;
import edu.xtu.androidbase.weaher.ui.weather.model.usercase.ProvinceUseCase;
import edu.xtu.androidbase.weaher.ui.weather.view.ICityView;
import rx.Subscriber;

/**
 * Created by huilin on 2016/12/12.
 */

public class CityPresenter {
    private ICityModel iCityModel;
    private ICityView iCityView;
    ProvinceUseCase provinceUseCase;
    CityUseCase cityList;
    public CityPresenter(ICityView iCityView) {
        this.iCityView = iCityView;
        iCityModel = new CityModelImpl();
    }
    public void showCityListView(int localFlag,WhereCondition whereCondition){


        if(localFlag == SelectCityRecycleAdapter.CITY){
            cityList = iCityModel.getCityList();
            CityRequest cityRequest = new CityRequest();
            cityRequest.setWhereCondition(whereCondition);
            cityList.createObservable(cityRequest)
                    .subscribe(new Subscriber<List<City>>() {
                        @Override
                        public void onCompleted() {
                            iCityView.success();
                        }

                        @Override
                        public void onError(Throwable e) {
                            iCityView.error(e.getMessage());
                        }

                        @Override
                        public void onNext(List<City> cities) {
                            iCityView.showCityListView(cities);
                        }
                    });
            cityList.unsubscribe();
        }else if(localFlag == SelectCityRecycleAdapter.PROVINCE){
            provinceUseCase = iCityModel.getProvinceList();
            ProvinceRequest provinceRequest = new ProvinceRequest();
            provinceRequest.setWhereCondition(whereCondition);
            provinceUseCase.createObservable(provinceRequest)
                    .subscribe(new Subscriber<List<Province>>() {
                        @Override
                        public void onCompleted() {
                            iCityView.success();
                        }

                        @Override
                        public void onError(Throwable e) {
                            iCityView.error(e.getMessage());
                        }

                        @Override
                        public void onNext(List<Province> provinces) {
                            iCityView.showProvinceView(provinces);
                        }
                    });
            provinceUseCase.unsubscribe();
        }
    }


}
