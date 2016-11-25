package edu.xtu.androidbase.weaher.ui.weather.model.usercase;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.SparseArray;

import java.util.List;

import edu.xtu.androidbase.weaher.ui.weather.domain.AddressBean;
import edu.xtu.androidbase.weaher.ui.weather.domain.GaoDeAddressBean;
import edu.xtu.androidbase.weaher.ui.weather.model.request.HomeRequest;
import edu.xtu.androidbase.weaher.util.AppInfo;
import edu.xtu.androidbase.weaher.util.Retrofit.ApiService;
import edu.xtu.androidbase.weaher.util.RxUtil.RxHelp;
import edu.xtu.androidbase.weaher.util.RxUtil.UseCase;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by huilin on 2016/11/12.
 */
public class LocationUserCase extends UseCase<GaoDeAddressBean, HomeRequest> {
    @Override
    public Observable<GaoDeAddressBean> createObservable(HomeRequest homeRequest) {
        return getLocationObservable(homeRequest.getLocationManager());
    }


    private Observable<GaoDeAddressBean> getLocationObservable(final LocationManager locationManager) {
        return Observable.create(new Observable.OnSubscribe<Location>() {
            @Override
            public void call(final Subscriber<? super Location> subscriber) {
                LocationListener locationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        if (location != null) {
                            if (!subscriber.isUnsubscribed()) {
                                subscriber.onNext(location);
                                subscriber.unsubscribe();
                                locationManager.removeUpdates(this);
                            }

                        }
                    }

                    @Override
                    public void onStatusChanged(String s, int i, Bundle bundle) {

                    }

                    @Override
                    public void onProviderEnabled(String s) {

                    }

                    @Override
                    public void onProviderDisabled(String s) {

                    }
                };
                List<String> providers = locationManager.getProviders(true);
                String locationProvider = "";
//                if (providers.contains(LocationManager.GPS_PROVIDER)) {
//                    locationProvider = LocationManager.GPS_PROVIDER;
//                } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
//                    locationProvider = LocationManager.NETWORK_PROVIDER;
//                } else {
//                    //没有提供者
//                    subscriber.onError(new Throwable("不能定位位置"));
//                }
                final Criteria locationCriteria = new Criteria();
                locationCriteria.setAccuracy(Criteria.ACCURACY_COARSE);
                locationCriteria.setPowerRequirement(Criteria.POWER_LOW);
                String locationProvider1 = locationManager.getBestProvider(locationCriteria, true);
                locationManager.requestLocationUpdates(locationProvider1, 1000, 0, locationListener);


            }
        }).retry(new Func2<Integer, Throwable, Boolean>() {
            @Override
            public Boolean call(Integer integer, Throwable throwable) {
                return integer < 3;
            }
        }).concatMap(new Func1<Location, Observable<? extends GaoDeAddressBean>>() {
            @Override
            public Observable<? extends GaoDeAddressBean> call(Location location) {
                return ApiService.getInstanct().getLocationInfo(location);
            }
        });
    }


}
