package edu.xtu.androidbase.weaher.ui.test;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import edu.xtu.androidbase.weaher.BuildConfig;
import edu.xtu.androidbase.weaher.R;
import edu.xtu.androidbase.weaher.ui.base.BaseActivity;
import edu.xtu.androidbase.weaher.ui.weather.activity.HomeActivity;
import edu.xtu.androidbase.weaher.ui.weather.domain.SelectCity;
import edu.xtu.androidbase.weaher.ui.weather.domain.Weather;
import edu.xtu.androidbase.weaher.ui.weather.domain.WeatherAPI;
import edu.xtu.androidbase.weaher.util.AppMethods;
import edu.xtu.androidbase.weaher.util.DbManager;
import edu.xtu.androidbase.weaher.util.LogUtils;
import edu.xtu.androidbase.weaher.util.Retrofit.ApiService;
import edu.xtu.androidbase.weaher.util.Retrofit.HttpClientListner;
import edu.xtu.androidbase.weaher.util.Retrofit.HttpModel;
import edu.xtu.androidbase.weaher.util.Retrofit.HttpSubscriber;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;
import rx.functions.Func1;

/**
 * Created by huilin on 2016/8/15.
 */
public class TestActivity extends BaseActivity {
    SwipeRefreshLayout s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏android系统的状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 隐藏应用程序的标题栏，即当前activity的标题栏 this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_test);
        ImageView imageView = (ImageView) findViewById(R.id.img_bg);
        imageView.animate().alpha(0.3f).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                finish();
                Intent intent = new Intent(mContext, HomeActivity.class);
                startActivity(intent);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).setDuration(2000).start();
//        Log.v("xxxxxxx","1234");
//        ApiService.getInstanct().getWeather("北京市", BuildConfig.HE_FENG_KEY, new HttpSubscriber<WeatherAPI>() {
//            @Override
//            public void error(HttpModel httpModel) {
//                AppMethods.shwoToast(httpModel.getMsg());
//            }
//
//            @Override
//            public void success(WeatherAPI weatherAPI) {
//                LogUtils.d(TAG,weatherAPI.toString());
//            }
//        });
//        Intent intent = new Intent(mContext, HomeActivity.class);
//        startActivity(intent);
//        overridePendingTransition(R.anim.alpha_enter,R.anim.alpha_out);
        final List<SelectCity> selectCities = new ArrayList<>();
        SelectCity selectCity = new SelectCity();
        selectCity.setCityName("北京");
        SelectCity selectCity1 = new SelectCity();
        selectCity1.setCityName("北京市");
        selectCities.add(selectCity);
        selectCities.add(selectCity1);
//        Observable.defer(new Func0<Observable<SelectCity>>() {
//            @Override
//            public Observable<SelectCity> call() {
//                return Observable.from(selectCities);
//            }
//        })
//                .flatMap(new Func1<SelectCity, Observable<Weather>>() {
//                    @Override
//                    public Observable<Weather> call(SelectCity selectCity) {
//                        LogUtils.d(TAG, selectCity.toString());
//                        return ApiService.getInstanct().getWeather(selectCity.getCityName())
//                                .map(new Func1<WeatherAPI, Weather>() {
//                                    @Override
//                                    public Weather call(WeatherAPI weatherAPI) {
//                                        return weatherAPI.mHeWeatherDataService30s.get(0);
//                                    }
//                                });
//
//                    }
//                })
//                .filter(new Func1<Weather, Boolean>() {
//                    @Override
//                    public Boolean call(Weather weather) {
//                        return "unknown city".equals(weather.status);
//                    }
//                })
//                .subscribe(new Subscriber<Weather>() {
//                    @Override
//                    public void onCompleted() {
//                        LogUtils.d(TAG,"onCompleted");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Weather weather) {
//                        LogUtils.d(TAG,"onNext");
//                    }
//                });



    }

    public void showFirst(View view) {
        finish();
        Intent intent = new Intent(mContext, HomeActivity.class);
        startActivity(intent);
//        overridePendingTransition(R.anim.left_in,R.anim.left_out);


//        Bundle bundle = new Bundle();
//        bundle.putString("test","test");
//        Intent intent = new Intent(this,TestActivity.class);
//        intent.putExtra(BUNDLE_DATA,bundle);
//        startActivity(intent);
//        Bundle bundle =new Bundle();
//        bundle.putInt(HomeActivity.SELECT_INDEX,2);
//        Fragment fragment = new Fragment1();
//        bundle.putSerializable("xx",fragment.getClass());
//        TestHomeActivity.openHomeActivity(mContext,null);
//        TerminalActivity.show(mContext,Fragment1.class,null);
//        Bundle bundle =new Bundle();
//        bundle.putString("test","哈哈哈");
//        TerminalActivity.showForResult(mContext,Fragment2.class,bundle,100);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (data!=null){
//            Log.v(TAG,data.getBundleExtra("bundle_data").getString("test"));
//        }
//    }
}
