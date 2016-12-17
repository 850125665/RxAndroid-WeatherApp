package edu.xtu.androidbase.weaher.ui.weather.view;

import java.util.List;

import edu.xtu.androidbase.weaher.ui.weather.domain.SelectCity;
import edu.xtu.androidbase.weaher.ui.weather.domain.Weather;
import edu.xtu.androidbase.weaher.ui.weather.domain.WeatherAPI;

/**
 * Created by huilin on 2016/11/12.
 */
public interface IMainView extends IBaseView{

    public void showWeatherList(List<Weather> weathers);


}
