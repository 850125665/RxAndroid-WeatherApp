package edu.xtu.androidbase.weaher.presenter;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

import edu.xtu.androidbase.weaher.adapter.SelectCityRecycleAdapter;
import edu.xtu.androidbase.weaher.domain.City;
import edu.xtu.androidbase.weaher.domain.Province;
import edu.xtu.androidbase.weaher.model.SelectSelectCityModelImpl;
import edu.xtu.androidbase.weaher.model.ISelectCityModel;
import edu.xtu.androidbase.weaher.model.request.CityRequest;
import edu.xtu.androidbase.weaher.model.request.ProvinceRequest;
import edu.xtu.androidbase.weaher.model.usercase.CityUseCase;
import edu.xtu.androidbase.weaher.model.usercase.ProvinceUseCase;
import edu.xtu.androidbase.weaher.ui.weather.view.ISelectCityView;
import rx.Subscriber;

/**
 * Created by huilin on 2016/12/12.
 */

public class SelectCityPresenter {
    private ISelectCityModel iSelectCityModel;
    private ISelectCityView iSelectCityView;
    ProvinceUseCase provinceUseCase;
    CityUseCase cityList;
    public SelectCityPresenter(ISelectCityView iSelectCityView) {
        this.iSelectCityView = iSelectCityView;
        iSelectCityModel = new SelectSelectCityModelImpl();
    }
    public void showCityListView(int localFlag,WhereCondition whereCondition){


        if(localFlag == SelectCityRecycleAdapter.CITY){
            cityList = iSelectCityModel.getCityList();
            CityRequest cityRequest = new CityRequest();
            cityRequest.setWhereCondition(whereCondition);
            cityList.createObservable(cityRequest)
                    .subscribe(new Subscriber<List<City>>() {
                        @Override
                        public void onCompleted() {
                            iSelectCityView.success();
                        }

                        @Override
                        public void onError(Throwable e) {
                            iSelectCityView.error(e.getMessage());
                        }

                        @Override
                        public void onNext(List<City> cities) {
                            iSelectCityView.showCityListView(cities);
                        }
                    });
            cityList.unsubscribe();
        }else if(localFlag == SelectCityRecycleAdapter.PROVINCE){
            provinceUseCase = iSelectCityModel.getProvinceList();
            ProvinceRequest provinceRequest = new ProvinceRequest();
            provinceRequest.setWhereCondition(whereCondition);
            provinceUseCase.createObservable(provinceRequest)
                    .subscribe(new Subscriber<List<Province>>() {
                        @Override
                        public void onCompleted() {
                            iSelectCityView.success();
                        }

                        @Override
                        public void onError(Throwable e) {
                            iSelectCityView.error(e.getMessage());
                        }

                        @Override
                        public void onNext(List<Province> provinces) {
                            iSelectCityView.showProvinceView(provinces);
                        }
                    });
            provinceUseCase.unsubscribe();
        }
    }


}
