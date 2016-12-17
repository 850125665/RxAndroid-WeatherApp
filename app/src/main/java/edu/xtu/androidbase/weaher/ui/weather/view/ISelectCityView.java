package edu.xtu.androidbase.weaher.ui.weather.view;

import java.util.List;

import edu.xtu.androidbase.weaher.ui.weather.domain.City;
import edu.xtu.androidbase.weaher.ui.weather.domain.Province;

/**
 * Created by huilin on 2016/12/12.
 */

public interface ISelectCityView extends IBaseView {
    public void showCityListView(List<City> cities);
    public void showProvinceView(List<Province> provinces);
}
