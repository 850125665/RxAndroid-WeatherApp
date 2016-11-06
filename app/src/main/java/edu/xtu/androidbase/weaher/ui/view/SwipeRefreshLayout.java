package edu.xtu.androidbase.weaher.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by skyar@live.cn.If you have any questions please contact me!
 */
public class SwipeRefreshLayout
        extends android.support.v4.widget.SwipeRefreshLayout {

    private GestureDetector mGestureDetector;

    public SwipeRefreshLayout(Context context) {
        super(context);
        init();
    }

    public SwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mGestureDetector = new GestureDetector(getContext(),
                new YScrollDetector());
        setFadingEdgeLength(0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev) && mGestureDetector
                .onTouchEvent(ev);
    }

    private class YScrollDetector
            extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                                float distanceY) {

            return Math.abs(distanceY) >= Math.abs(distanceX);
        }
    }

}
