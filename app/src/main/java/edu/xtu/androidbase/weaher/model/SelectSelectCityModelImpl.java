package edu.xtu.androidbase.weaher.model;

import edu.xtu.androidbase.weaher.model.usercase.CityUseCase;
import edu.xtu.androidbase.weaher.model.usercase.ProvinceUseCase;

/**
 * Created by huilin on 2016/12/12.
 */

public class SelectSelectCityModelImpl implements ISelectCityModel {

    private CityUseCase cityUseCase;
    private ProvinceUseCase provinceUseCase;

    public SelectSelectCityModelImpl() {
        cityUseCase = new CityUseCase();
        provinceUseCase = new ProvinceUseCase();
    }


    @Override
    public CityUseCase getCityList() {
        return cityUseCase;
    }

    @Override
    public CityUseCase getCityListById(String id) {
        return cityUseCase;
    }

    @Override
    public ProvinceUseCase getProvinceList() {
        return provinceUseCase;
    }
}
