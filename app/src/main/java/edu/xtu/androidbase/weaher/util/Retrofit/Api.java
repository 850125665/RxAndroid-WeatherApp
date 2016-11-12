package edu.xtu.androidbase.weaher.util.Retrofit;

import java.util.List;

import edu.xtu.androidbase.weaher.ui.weather.domain.AddressBean;
import edu.xtu.androidbase.weaher.ui.weather.domain.GaoDeAddressBean;
import edu.xtu.androidbase.weaher.ui.weather.domain.Weather;
import edu.xtu.androidbase.weaher.ui.weather.domain.WeatherAPI;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by huilin on 2016/11/6.
 */
public interface Api {

    @GET("weather")
    Observable<WeatherAPI> getWeather(@Query("city") String city, @Query("key") String key);

    @GET("http://restapi.amap.com/v3/geocode/regeo")
    Observable<GaoDeAddressBean> getLocationInfo(@Query("location") String location, @Query("key") String key, @Query("output") String out);

}
