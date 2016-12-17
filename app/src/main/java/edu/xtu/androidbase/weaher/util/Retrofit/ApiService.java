package edu.xtu.androidbase.weaher.util.Retrofit;

import android.location.Location;

import edu.xtu.androidbase.weaher.BuildConfig;
import edu.xtu.androidbase.weaher.ui.weather.domain.GaoDeAddressBean;
import edu.xtu.androidbase.weaher.ui.weather.domain.Weather;
import edu.xtu.androidbase.weaher.ui.weather.domain.WeatherAPI;
import edu.xtu.androidbase.weaher.util.RxUtil.RxHelp;
import rx.Observable;

/**
 * Created by huilin on 2016/11/6.
 */
public class ApiService {
    private String TAG = ApiService.class.getSimpleName();
    private Api mApi;
    private ApiService(){
        mApi = RetrofitClient.getInstance().retrofit.create(Api.class);
    }

    private static class ApiServiceHodle{
        public static ApiService INSTANCT = new ApiService();
    }

    public static ApiService getInstanct(){
        return ApiServiceHodle.INSTANCT;
    }

   public void getWeather(String city , String key,HttpSubscriber<WeatherAPI> httpSubscriber){

       mApi.getWeather(city,key).compose(RxHelp.<WeatherAPI>onlineSchedul()).subscribe(httpSubscriber);
//        mApi.getWeather(city,key).compose(RxHelp.<WeatherAPI>applyExecutorSchedulers()).subscribe(this.httpCallBack(httpClientListner));
   }

    public Observable<GaoDeAddressBean>  getLocationInfo(Location location){
        return mApi.getLocationInfo(location.getLongitude() + "," + location.getLatitude(), BuildConfig.GAO_DE_LOCATION,"json").compose(RxHelp.<GaoDeAddressBean>onlineSchedul());
    }
    public Observable<WeatherAPI> getWeather(String city){
        return  mApi.getWeather(city,BuildConfig.HE_FENG_KEY).compose(RxHelp.<WeatherAPI>onlineSchedul());
    }

}
