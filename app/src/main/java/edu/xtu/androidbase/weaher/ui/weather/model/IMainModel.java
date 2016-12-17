package edu.xtu.androidbase.weaher.ui.weather.model;

import java.util.List;

import edu.xtu.androidbase.weaher.ui.weather.domain.SelectCity;
import edu.xtu.androidbase.weaher.ui.weather.domain.Weather;
import edu.xtu.androidbase.weaher.ui.weather.domain.WeatherAPI;
import edu.xtu.androidbase.weaher.ui.weather.model.request.MainRequest;
import rx.Observable;

/**
 * Created by huilin on 2016/12/14.
 */

public interface IMainModel {

    public Observable<Weather> showWeatherList(MainRequest mainRequest);

}
