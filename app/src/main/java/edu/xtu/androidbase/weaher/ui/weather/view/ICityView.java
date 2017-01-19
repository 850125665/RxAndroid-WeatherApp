package edu.xtu.androidbase.weaher.ui.weather.view;

import java.util.List;

import edu.xtu.androidbase.weaher.domain.SelectCity;

/**
 * Created by huilin on 2016/12/14.
 */

public interface ICityView extends IBaseView{
    void showListCity(List<SelectCity> selectCities);
}
