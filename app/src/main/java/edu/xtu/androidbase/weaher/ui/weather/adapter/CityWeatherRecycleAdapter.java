package edu.xtu.androidbase.weaher.ui.weather.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.xtu.androidbase.weaher.R;
import edu.xtu.androidbase.weaher.ui.weather.domain.Weather;

/**
 * Created by huilin on 2016/11/21.
 */
public class CityWeatherRecycleAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Weather> weathers = new ArrayList<>();
    private OnClickListener onclickListenr;
    private int TYPE_HEAD = 0;
    private int TYPE_GRID = 1;

    public void setDatas(Collection<Weather> datas) {
        weathers.clear();
        if (datas != null) {
            weathers.addAll(datas);
        }
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnClickListener onclickListenr) {
        this.onclickListenr = onclickListenr;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        RecyclerView.ViewHolder viewHolder;
        View view = null;
        if (viewType == TYPE_HEAD) {;
            view = LayoutInflater.from(mContext).inflate( R.layout.item_head, parent,false);
            viewHolder = new HeadViewHolder(view);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_weather, parent,false);
            viewHolder = new GridViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        int itemViewType = this.getItemViewType(position);
        if (itemViewType == TYPE_HEAD) {

        } else {
            GridViewHolder holder1 = (GridViewHolder) holder;
            ((GridViewHolder) holder).cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onclickListenr != null) {

                        onclickListenr.OnClickItem(holder,position);
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_HEAD : TYPE_GRID;
    }


    public static class GridViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.content_img)
        public ImageView contentImg;
        @Bind(R.id.content_tv)
        TextView contentTv;
        @Bind(R.id.card_view)
        CardView cardView;

        public GridViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    public interface OnClickListener {
        void OnClickItem(RecyclerView.ViewHolder holder, int position);
    }

    public static class HeadViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_city)
        TextView tvCity;
        @Bind(R.id.tv_weather)
        TextView tvWeather;
        @Bind(R.id.img_weather)
        ImageView imgWeather;
        @Bind(R.id.tv_weather_msg)
        TextView tvWeatherMsg;

        public HeadViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
