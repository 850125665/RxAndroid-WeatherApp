package edu.xtu.androidbase.weaher.ui.weather.model;

import java.util.List;

import edu.xtu.androidbase.weaher.ui.weather.domain.City;
import edu.xtu.androidbase.weaher.ui.weather.model.usercase.CityUseCase;
import edu.xtu.androidbase.weaher.ui.weather.model.usercase.ProvinceUseCase;

/**
 * Created by huilin on 2016/12/12.
 */

public interface ICityModel {
    public CityUseCase getCityList();
    public CityUseCase getCityListById(String id);
    public ProvinceUseCase getProvinceList();

}
