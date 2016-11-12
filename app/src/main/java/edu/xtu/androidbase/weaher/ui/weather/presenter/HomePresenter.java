package edu.xtu.androidbase.weaher.ui.weather.presenter;

import android.content.Context;
import android.content.res.AssetManager;
import android.location.LocationListener;
import android.location.LocationManager;

import edu.xtu.androidbase.weaher.ui.weather.domain.AddressBean;
import edu.xtu.androidbase.weaher.ui.weather.domain.CityBean;
import edu.xtu.androidbase.weaher.ui.weather.domain.GaoDeAddressBean;
import edu.xtu.androidbase.weaher.ui.weather.model.HomeModelImpl;
import edu.xtu.androidbase.weaher.ui.weather.model.IHomeModel;
import edu.xtu.androidbase.weaher.ui.weather.model.request.HomeRequest;
import edu.xtu.androidbase.weaher.ui.weather.model.usercase.LocationUserCase;
import edu.xtu.androidbase.weaher.ui.weather.view.IHomeView;
import edu.xtu.androidbase.weaher.util.AppInfo;
import edu.xtu.androidbase.weaher.util.LogUtils;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by huilin on 2016/11/12.
 */
public class HomePresenter {

    private String TAG = this.getClass().getSimpleName();
    private IHomeModel iHomeModel;
    private IHomeView iHomeView;

    public HomePresenter(IHomeView iHomeView) {
        iHomeModel = new HomeModelImpl();
        this.iHomeView = iHomeView;
    }

    public void setCityName(){
        LocationManager locationManager = (LocationManager) AppInfo.getAppInstant().getMyContext().getSystemService(Context.LOCATION_SERVICE);
        AssetManager assetManager = AppInfo.getAppInstant().getMyContext().getAssets();
        LocationUserCase cityInfo = iHomeModel.getCityInfo();
        cityInfo.subscriber(new Subscriber<GaoDeAddressBean>() {
            @Override
            public void onCompleted() {
                iHomeView.success();
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.i(TAG,e.getMessage());
                iHomeView.error(e.getMessage());
            }

            @Override
            public void onNext(GaoDeAddressBean addressBean) {
                iHomeView.setCityName(addressBean.getRegeocode().getAddressComponent().getProvince());
            }
        },new HomeRequest(locationManager,assetManager));
    }
}
