package edu.xtu.androidbase.weaher.model;

import edu.xtu.androidbase.weaher.domain.Weather;
import edu.xtu.androidbase.weaher.model.request.MainRequest;
import rx.Observable;

/**
 * Created by huilin on 2016/12/14.
 */

public interface IMainModel {

    public Observable<Weather> showWeatherList(MainRequest mainRequest);

}
