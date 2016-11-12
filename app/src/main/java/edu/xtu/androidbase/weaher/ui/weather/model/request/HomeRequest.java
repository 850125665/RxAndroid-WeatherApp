package edu.xtu.androidbase.weaher.ui.weather.model.request;

import android.content.res.AssetManager;
import android.location.LocationManager;

/**
 * Created by huilin on 2016/11/12.
 */
public class HomeRequest {
    private LocationManager locationManager;
    private AssetManager assetManager;

    public LocationManager getLocationManager() {
        return locationManager;
    }

    public void setLocationManager(LocationManager locationManager) {
        this.locationManager = locationManager;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public HomeRequest(LocationManager locationManager, AssetManager assetManager) {

        this.locationManager = locationManager;
        this.assetManager = assetManager;
    }
}
