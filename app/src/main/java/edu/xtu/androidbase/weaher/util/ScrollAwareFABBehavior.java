package edu.xtu.androidbase.weaher.util;

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * Created by huilin on 2016/11/26.
 */

public class ScrollAwareFABBehavior extends FloatingActionButton.Behavior {

    private boolean isOutAnimation = false;

    public ScrollAwareFABBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes== ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
//
//        if(dyConsumed>0 && child.getVisibility() == View.VISIBLE){
//            //向上滑动
//            child.hide();
//        }else if(dyConsumed<0 && child.getVisibility() != View.VISIBLE){
//            child.show();
//        }
        if(dyConsumed>0 && !isOutAnimation ){
            animationOut(child);
        }else if(dyConsumed<0 ){
            animationIn(child);
        }

    }

    public void animationIn(FloatingActionButton floatingActionButton){
//        floatingActionButton.setVisibility(View.VISIBLE);
        floatingActionButton.animate().translationY(0).setInterpolator(new FastOutSlowInInterpolator()).start();
    }
    public void animationOut(final FloatingActionButton floatingActionButton){
        floatingActionButton.animate().translationY(floatingActionButton.getHeight()+getMargin(floatingActionButton)).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isOutAnimation = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isOutAnimation = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                isOutAnimation = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).start();
    }

    public int getMargin(FloatingActionButton floatingActionButton){
        int margin = 0;
        ViewGroup.LayoutParams layoutParams = floatingActionButton.getLayoutParams();
        if(layoutParams instanceof ViewGroup.MarginLayoutParams){
            margin = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        }
        return margin;
    }
}
