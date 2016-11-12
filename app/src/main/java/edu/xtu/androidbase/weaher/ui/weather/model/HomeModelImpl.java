package edu.xtu.androidbase.weaher.ui.weather.model;

import edu.xtu.androidbase.weaher.ui.weather.domain.AddressBean;
import edu.xtu.androidbase.weaher.ui.weather.model.request.HomeRequest;
import edu.xtu.androidbase.weaher.ui.weather.model.usercase.LocationUserCase;
import rx.Observer;

/**
 * Created by huilin on 2016/11/12.
 */
public class HomeModelImpl implements IHomeModel {

    @Override
    public LocationUserCase getCityInfo() {

        LocationUserCase locationUserCase = new LocationUserCase();
        return  locationUserCase;
    }


}
