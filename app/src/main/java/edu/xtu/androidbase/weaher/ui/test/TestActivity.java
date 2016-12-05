package edu.xtu.androidbase.weaher.ui.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;

import edu.xtu.androidbase.weaher.BuildConfig;
import edu.xtu.androidbase.weaher.R;
import edu.xtu.androidbase.weaher.ui.base.BaseActivity;
import edu.xtu.androidbase.weaher.ui.weather.activity.HomeActivity;
import edu.xtu.androidbase.weaher.ui.weather.domain.WeatherAPI;
import edu.xtu.androidbase.weaher.util.Retrofit.ApiService;
import edu.xtu.androidbase.weaher.util.Retrofit.HttpClientListner;

/**
 * Created by huilin on 2016/8/15.
 */
public class TestActivity extends BaseActivity {
    SwipeRefreshLayout s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Log.v("xxxxxxx","1234");
//        ApiService.getInstanct().getWeather("北京", BuildConfig.HE_FENG_KEY, new HttpClientListner<WeatherAPI>() {
//            @Override
//            public void success(WeatherAPI weatherAPI) {
//
//            }
//
//            @Override
//            public void error(Throwable error) {
//
//            }
//        });

    }

    public void showFirst(View view){
        Intent intent = new Intent(mContext, HomeActivity.class);
        startActivity(intent);

        overridePendingTransition(R.anim.dialog_enter,R.anim.dialog_out);
        finish();
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
