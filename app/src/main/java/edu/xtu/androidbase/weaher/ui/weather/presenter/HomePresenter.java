package edu.xtu.androidbase.weaher.ui.weather.presenter;

import android.content.Context;
import android.content.res.AssetManager;
import android.location.LocationManager;

import edu.xtu.androidbase.weaher.ui.weather.domain.GaoDeAddressBean;
import edu.xtu.androidbase.weaher.ui.weather.model.HomeModelImpl;
import edu.xtu.androidbase.weaher.ui.weather.model.IHomeModel;
import edu.xtu.androidbase.weaher.ui.weather.model.request.HomeRequest;
import edu.xtu.androidbase.weaher.ui.weather.model.usercase.LocationUserCase;
import edu.xtu.androidbase.weaher.ui.weather.view.IHomeView;
import edu.xtu.androidbase.weaher.util.AppInfo;
import edu.xtu.androidbase.weaher.util.LogUtils;
import edu.xtu.androidbase.weaher.util.Retrofit.HttpModel;
import edu.xtu.androidbase.weaher.util.Retrofit.HttpSubscriber;

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

        cityInfo.subscriber(new HttpSubscriber<GaoDeAddressBean>() {
            @Override
            public void error(HttpModel httpModel) {
                LogUtils.i(TAG,httpModel.getMsg());
                iHomeView.error(httpModel.getMsg());
            }

            @Override
            public void success(GaoDeAddressBean gaoDeAddressBean) {
                iHomeView.setCityName(gaoDeAddressBean.getRegeocode().getAddressComponent().getProvince());
                iHomeView.success();
            }
        },new HomeRequest(locationManager,assetManager));
    }
}
