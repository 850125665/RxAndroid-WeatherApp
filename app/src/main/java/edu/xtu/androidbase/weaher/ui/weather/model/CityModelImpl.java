package edu.xtu.androidbase.weaher.ui.weather.model;

import java.util.List;

import edu.xtu.androidbase.weaher.ui.weather.domain.City;
import edu.xtu.androidbase.weaher.ui.weather.model.usercase.CityUseCase;
import edu.xtu.androidbase.weaher.ui.weather.model.usercase.ProvinceUseCase;

/**
 * Created by huilin on 2016/12/12.
 */

public class CityModelImpl implements ICityModel {

    private CityUseCase cityUseCase;
    private ProvinceUseCase provinceUseCase;

    public CityModelImpl() {
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
