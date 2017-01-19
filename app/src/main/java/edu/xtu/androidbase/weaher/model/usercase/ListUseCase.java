package edu.xtu.androidbase.weaher.model.usercase;

import edu.xtu.androidbase.weaher.domain.GaoDeAddressBean;
import edu.xtu.androidbase.weaher.domain.WeatherAPI;
import edu.xtu.androidbase.weaher.model.request.HomeRequest;
import edu.xtu.androidbase.weaher.util.RxUtil.UseCase;
import rx.Observable;

/**
 * Created by huilin on 2016/11/18.
 */
public class ListUseCase extends UseCase<WeatherAPI,HomeRequest> {
    @Override
    public Observable<WeatherAPI> createObservable(HomeRequest homeRequest) {
        return null;
    }

    public Observable<WeatherAPI> getWeather(HomeRequest homeRequest,GaoDeAddressBean gaoDeAddressBean){
//        ApiService.getInstanct().getWeather();
        return null;
    }


}
