package edu.xtu.androidbase.weaher.model;

import edu.xtu.androidbase.weaher.model.usercase.CityUseCase;
import edu.xtu.androidbase.weaher.model.usercase.ProvinceUseCase;

/**
 * Created by huilin on 2016/12/12.
 */

public interface ISelectCityModel {
    public CityUseCase getCityList();
    public CityUseCase getCityListById(String id);
    public ProvinceUseCase getProvinceList();

}
