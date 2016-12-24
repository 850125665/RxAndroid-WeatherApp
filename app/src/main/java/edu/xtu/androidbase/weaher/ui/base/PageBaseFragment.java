package edu.xtu.androidbase.weaher.ui.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import edu.xtu.androidbase.weaher.util.AppInfo;
import edu.xtu.androidbase.weaher.util.AppMethods;
import edu.xtu.androidbase.weaher.util.BaseAdapter;
import edu.xtu.androidbase.weaher.util.view.LoadView;

/**
 * Created by huilin on 2016/12/23.
 */

public abstract class PageBaseFragment extends BaseFragment implements BaseAdapter.LoadMoreListener {

    public int PAGE_NUM = 10;
    /**
     * 刷新状况
     */
    public boolean isFresh = false;
    /**
     * 加载状况
     */
    public boolean isLoadMore = false;


    private BaseAdapter baseAdapter;

    private RecyclerView recyclerView;

    @Override
    protected void initData() {
        baseAdapter = getBaseAdapter();
        recyclerView = getRecycleView();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                        if(layoutManager instanceof LinearLayoutManager){
                            int lastCompletelyVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
                            if(lastCompletelyVisibleItemPosition == baseAdapter.getItemCount()-1){
                                loadMore();
                            }
                        }else if(layoutManager instanceof GridLayoutManager){
                            int lastCompletelyVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
                            if(lastCompletelyVisibleItemPosition == baseAdapter.getItemCount()-1){
                                loadMore();
                            }
                        }


            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

    }

    protected abstract RecyclerView getRecycleView();


    protected abstract BaseAdapter getBaseAdapter();

    @Override
    public void loadMore() {
        if(!swipeRefreshLayout.isRefreshing() && !isLoadMore && !isFresh){
            swipeRefreshLayout.setEnabled(false);
            isLoadMore = true;
            baseAdapter.loadMore = true;
            baseAdapter.notifyDataSetChanged();
            loadMoreNet();
        }
    }

    /**
     * 网络结果回调
     * @param isSuccess
     */
    public void netCallBack(boolean isSuccess){
        isLoadMore = !isSuccess;
        baseAdapter.loadMore = !isSuccess;
        isFresh = false;
        baseAdapter.netSuccess = isSuccess;
        baseAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setEnabled(true);
    }

    /**
     * 加载更多
     */
    protected abstract void loadMoreNet();

    /**
     * 是否是刷新数据
     * @return
     */
    public boolean isFreshData(){
        return !isLoadMore && (isFresh || swipeRefreshLayout.isRefreshing());
    }

    @Override
    protected void getNet(LoadView.IOnNetListener iOnNetListener) {
        isFresh = true;
    }

}
