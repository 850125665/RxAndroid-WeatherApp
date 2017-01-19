package edu.xtu.androidbase.weaher.ui.weather.view;

import java.util.List;

import edu.xtu.androidbase.weaher.domain.Weather;

/**
 * Created by huilin on 2016/11/12.
 */
public interface IMainView extends IBaseView{

    public void showWeatherList(List<Weather> weathers);


}
