package edu.xtu.androidbase.weaher.util.Retrofit;

import android.util.Log;

import edu.xtu.androidbase.weaher.ui.weather.domain.WeatherAPI;
import edu.xtu.androidbase.weaher.util.RxUtil.RxHelp;
import rx.Subscriber;

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

   public void getWeather(String city , String key,HttpClientListner<WeatherAPI> httpClientListner){

       mApi.getWeather(city,key).compose(RxHelp.<WeatherAPI>onlineSchedul()).subscribe(this.httpCallBack(httpClientListner));
//        mApi.getWeather(city,key).compose(RxHelp.<WeatherAPI>applyExecutorSchedulers()).subscribe(this.httpCallBack(httpClientListner));
   }

    public <T extends HttpModel> Subscriber<T> httpCallBack( final HttpClientListner httpClientListner){
        return new Subscriber<T>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG,e.getMessage());
                httpClientListner.error(e);
            }

            @Override
            public void onNext(T t) {
                Log.i(TAG,t.toString());
                //实际项目中code值判断成功失败
//                if(200 ==t.getCode()){
//
//                }else {
//
//                }
                httpClientListner.success(t);
            }
        };
    }
}
