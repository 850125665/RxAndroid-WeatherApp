package edu.xtu.androidbase.weaher.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.xtu.androidbase.weaher.R;

/**
 * Created by huilin on 2016/12/16.
 */

public abstract class BaseAdapter extends RecyclerView.Adapter {


    public boolean loadMore = false;
    public int COMMON_TYPE = 0;
    public int FOOT_TYPE = 1;
    private boolean isOpenLoadMore = false;
    public boolean netSuccess = true;

    private LoadMoreListener loadMoreListener;

    public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       if( viewType == FOOT_TYPE) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_foot, parent, false);
            return new FootViewHolder(inflate);
        }else {

           return getCommonViewHolder(parent,viewType);
       }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if(itemViewType == FOOT_TYPE){
            FootViewHolder footViewHolder = (FootViewHolder) holder;
            if(loadMore ){
                if(netSuccess == true){
                    footViewHolder.tvFoot.setClickable(false);
                    footViewHolder.tvFoot.setText("正在玩命加载中");
                    footViewHolder.progressBar.setVisibility(View.VISIBLE);
                }else{
                    footViewHolder.tvFoot.setClickable(true);
                    footViewHolder.tvFoot.setText("点击加载更多");
                    footViewHolder.progressBar.setVisibility(View.GONE);
                    footViewHolder.tvFoot.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            netSuccess = true;
                            if(loadMoreListener!=null){
                                loadMoreListener.loadMore();
                            }
                        }
                    });

                }
                footViewHolder.tvFoot.setVisibility(View.VISIBLE);
            }else{
                footViewHolder.tvFoot.setText("end");
                footViewHolder.progressBar.setVisibility(View.GONE);
            }
        }
        onBindContentView(holder,position);
    }

    protected abstract void onBindContentView(RecyclerView.ViewHolder holder, int position);

    @Override
    public int getItemViewType(int position) {

        return isOpenLoadMore && position >= getItemCount()-1 ? FOOT_TYPE : getType();
    }

    public  void loadMore() {
        loadMore = true;
        if(loadMoreListener!=null ){
            loadMoreListener.loadMore();
        }
    }

    @Override
    public int getItemCount() {
        return getCount()+(isOpenLoadMore?1:0);
    }

    /**
     * 获取实际数据大小
     * @return
     */
    public abstract int getCount();

    /**
     * 开启加载更多设置
     * @param openLoadMore
     */
    public void setOpenLoadMore(boolean openLoadMore) {
        isOpenLoadMore = openLoadMore;
    }

    /**
     *获取实际加载的viewHolder
     * @param viewGroup
     * @param viewType
     * @return
     */
    public abstract RecyclerView.ViewHolder getCommonViewHolder(ViewGroup viewGroup, int viewType);

    /**
     * 除了foot后的其他布局类型，可重写
     * @return
     */
    public int getType() {
        return COMMON_TYPE;
    }


    /**
     * footView
     */
    static class FootViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_foot)
        TextView tvFoot;
        @Bind(R.id.progress_bar)
        ProgressBar progressBar;

        FootViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    /**
     * 加载更多接口
     */
    public interface LoadMoreListener{
        public void loadMore();
    }
}
