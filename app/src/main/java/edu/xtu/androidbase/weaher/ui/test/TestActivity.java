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


/**
 * Created by huilin on 2016/8/15.
 */
public class TestActivity extends BaseActivity {
    SwipeRefreshLayout s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
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

//



    }

    public void showFirst(View view) {
        finish();
        Intent intent = new Intent(mContext, HomeActivity.class);
        startActivity(intent);
    }
}
