package edu.xtu.androidbase.weaher.ui.weather.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.xtu.androidbase.weaher.R;

/**
 * Created by huilin on 2016/12/1.
 */

public class WeatherDetailRecycleAdapter extends RecyclerView.Adapter<WeatherDetailRecycleAdapter.ViewHolder> {

    private Context mContext;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        View view = LayoutInflater.from(mContext).inflate( R.layout.item_weather_detail, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_date)
        TextView tvDate;
        @Bind(R.id.tv_fan)
        TextView tvFan;
        @Bind(R.id.tv_day)
        TextView tvDay;
        @Bind(R.id.img_day)
        ImageView imgDay;
        @Bind(R.id.tv_night)
        TextView tvNight;
        @Bind(R.id.img_night)
        ImageView imgNight;
        @Bind(R.id.card_view)
        CardView cardView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
