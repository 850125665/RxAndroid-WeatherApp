package edu.xtu.androidbase.weaher.ui.test;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;

import edu.xtu.androidbase.weaher.util.AppInfo;
import edu.xtu.androidbase.weaher.util.AppMethods;
import edu.xtu.androidbase.weaher.util.LogUtils;

import static android.support.v7.widget.helper.ItemTouchHelper.ACTION_STATE_IDLE;
import static android.support.v7.widget.helper.ItemTouchHelper.LEFT;

/**
 * Created by huilin on 2016/12/15.
 */

public class TouchListener implements RecyclerView.OnItemTouchListener {

    private String TAG = getClass().getSimpleName();

    private GestureDetectorCompat gestureDetectorCompat;

    private RecyclerView recyclerView;

    private OnClickListener onClickListener;

    private ItemTouchHelper itemTouchHelper;

    private List list;

    private RecyclerView.Adapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    ViewPager viewPager;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public TouchListener(RecyclerView recyclerView, final List list, final RecyclerView.Adapter adapter) {
        this.list = list;
        this.adapter = adapter;
        this.recyclerView = recyclerView;
        gestureDetectorCompat = new GestureDetectorCompat(recyclerView.getContext(), new ItemTouchHelpGestureListener());
//        ViewParent v = recyclerView.getParent();
//        while (v != null) {
//            LogUtils.d(TAG, "onTouchEvent---" + v.getClass().getSimpleName());
//            if (v instanceof ViewPager) {
//                viewPager = (ViewPager) v;
//                break;
//            } else if (v instanceof SwipeRefreshLayout) {
//                swipeRefreshLayout = (SwipeRefreshLayout) v;
//            }
//            v = getParent(v);
//        }
        List<ViewParent> viewParents = new ArrayList<>();
        viewParents = AppMethods.getParentView(recyclerView.getParent(), viewParents);
        for (ViewParent parent : viewParents) {
            if (parent instanceof ViewPager) {
                viewPager = (ViewPager) parent;
            } else if (parent instanceof SwipeRefreshLayout) {
                swipeRefreshLayout = (SwipeRefreshLayout) parent;
            }
        }

    }

    float downX = 0;
    float downY = 0;

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = e.getX();
                downY = e.getY();
                if (viewPager != null) {
                    viewPager.requestDisallowInterceptTouchEvent(true);
                }
                if (swipeRefreshLayout != null) {
                    swipeRefreshLayout.requestDisallowInterceptTouchEvent(true);
                }
                LogUtils.d(TAG, "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:

                LogUtils.d(TAG, "x-" + downX + "   x--" + e.getX());
                LogUtils.d(TAG, "y-" + downY + "   y--" + e.getY());
                if (e.getX() - downX >0) {
                    if (viewPager != null) {

                        viewPager.requestDisallowInterceptTouchEvent(false);
                    }
                    if (swipeRefreshLayout != null) {

                        swipeRefreshLayout.requestDisallowInterceptTouchEvent(false);
                    }
                }
//
                LogUtils.d(TAG, "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                if (viewPager != null) {

                    viewPager.requestDisallowInterceptTouchEvent(false);
                }
                if (swipeRefreshLayout != null) {

                    swipeRefreshLayout.requestDisallowInterceptTouchEvent(false);
                }
                LogUtils.d(TAG, "ACTION_UP");
                break;
        }
        boolean b = gestureDetectorCompat.onTouchEvent(e);
        return b;
    }

    public ViewParent getParent(ViewParent view) {
        return view.getParent();
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            int i = 10;
            LogUtils.d(TAG, "onTouchEvent" + i);
//            while ((i--)>0){
//                LogUtils.d(TAG,"onTouchEvent"+recyclerView.getParent().getClass().getSimpleName());
//                if(recyclerView.getParent() instanceof ViewPager){
//                    LogUtils.d(TAG,"onTouchEvent"+i);
//                    break;
//                }
//            }
//            recyclerView.getParent().requestDisallowInterceptTouchEvent(true);
        }
        gestureDetectorCompat.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public class ItemTouchHelpGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            View childViewUnder = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (childViewUnder != null) {
                RecyclerView.ViewHolder childViewHolder = recyclerView.getChildViewHolder(childViewUnder);
                int childLayoutPosition = recyclerView.getChildLayoutPosition(childViewUnder);
                if (onClickListener != null) {

                    onClickListener.onItemClickListener(childViewHolder, childLayoutPosition);
                }
                LogUtils.d(TAG, "onSingleTapUp");
                return false;

            }
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            LogUtils.d(TAG, "onScroll" + "distanceX" + distanceX + " distanceY" + distanceY);

            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            LogUtils.d(TAG, "onShowPress");
        }

        @Override
        public void onLongPress(MotionEvent e) {
            View childViewUnder = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (childViewUnder != null) {
                RecyclerView.ViewHolder childViewHolder = recyclerView.getChildViewHolder(childViewUnder);
                int childLayoutPosition = recyclerView.getChildLayoutPosition(childViewUnder);
                if (onClickListener != null) {

                    onClickListener.onItemLongClickListener(childViewHolder, childLayoutPosition);
                   if(swipeRefreshLayout!=null){
                       swipeRefreshLayout.setEnabled(false);
                   }
                }
                LogUtils.d(TAG, "onLongPress");

            }


        }
    }

    public interface OnClickListener {
        <T extends RecyclerView.ViewHolder> void onItemClickListener(T t, int position);

        <T extends RecyclerView.ViewHolder> void onItemLongClickListener(T t, int position);
    }

    public void dragItem(final SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                LogUtils.d(TAG,"getMovementFlags");
                if(swipeRefreshLayout!=null){
                    swipeRefreshLayout.setEnabled(false);
                }
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                int dragFlag = 0;
                int swipeFlag = 0;
                if (layoutManager instanceof LinearLayoutManager) {

                    dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    swipeFlag = ItemTouchHelper.START;
                } else if (layoutManager instanceof GridLayoutManager) {
                    dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END;
                }
                return makeMovementFlags(dragFlag, swipeFlag);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                LogUtils.d(TAG, "onMove");
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                list.add(to, list.remove(from));
                adapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int adapterPosition = viewHolder.getAdapterPosition();
                adapter.notifyItemRemoved(adapterPosition);
                list.remove(adapterPosition);
                LogUtils.d(TAG, "onSwiped");
            }

            @Override
            public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {
                super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
                LogUtils.d(TAG, "onMoved");

            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);

                LogUtils.d(TAG, "onSelectedChanged");
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                LogUtils.d(TAG, "clearView");
                if (swipeRefreshLayout != null) {
                    if (!swipeRefreshLayout.isEnabled()) {
                        AppInfo.getAppInstant().getUiHandler().postDelayed(new TimerTask() {
                            @Override
                            public void run() {
                                swipeRefreshLayout.setEnabled(true);
                            }
                        }, 200);
                    }


                }
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

}
