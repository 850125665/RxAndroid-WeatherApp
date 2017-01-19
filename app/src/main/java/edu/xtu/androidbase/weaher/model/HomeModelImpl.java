package edu.xtu.androidbase.weaher.model;

import edu.xtu.androidbase.weaher.model.usercase.LocationUserCase;

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
