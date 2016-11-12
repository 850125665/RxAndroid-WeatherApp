package edu.xtu.androidbase.weaher.ui.weather.model;

import edu.xtu.androidbase.weaher.ui.weather.domain.AddressBean;
import edu.xtu.androidbase.weaher.ui.weather.model.usercase.LocationUserCase;
import rx.Observable;

/**
 * Created by huilin on 2016/11/12.
 */
public interface IHomeModel {

    LocationUserCase getCityInfo();

}
