package edu.xtu.androidbase.weaher.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;

/**
 * Created by huilin on 2016/12/26.
 */

public class CircularAnimUtil {

    /**
     *
     * @param view
     * @param startRadius
     * @param durTime
     */
    public static void show(View view,float startRadius,long durTime,AnimatorListenerAdapter animatorListenerAdapter){
        int cx = (view.getLeft()+view.getRight())/2;
        int cy = (view.getTop()+view.getBottom())/2;
        float endRadius = (float) Math.hypot(view.getWidth(),view.getHeight());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(view, cx, cy, startRadius, endRadius);
            circularReveal.setDuration(durTime);
            circularReveal.addListener(animatorListenerAdapter);
            circularReveal.start();

        }else{
            view.setVisibility(View.VISIBLE);
        }
    }

    public static void hide(View view,float endRadius,long durTime,AnimatorListenerAdapter animatorListenerAdapter){
        int cx = (view.getLeft()+view.getRight())/2;
        int cy = (view.getTop()+view.getBottom())/2;
        float initRadius = (float) Math.hypot(view.getWidth(),view.getHeight());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(view, cx, cy, initRadius, endRadius);
            circularReveal.setDuration(durTime);
            circularReveal.addListener(animatorListenerAdapter);
            circularReveal.start();
        }else{
            view.setVisibility(View.GONE);
        }
    }




}
