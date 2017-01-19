package edu.xtu.androidbase.weaher.adapter;

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
import edu.xtu.androidbase.weaher.domain.Weather;

/**
 * Created by huilin on 2016/12/1.
 */

public class WeatherDetailRecycleAdapter extends RecyclerView.Adapter<WeatherDetailRecycleAdapter.ViewHolder> {

    private Context mContext;

    private List<Weather.DailyForecastEntity> forecastEntities = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        View view = LayoutInflater.from(mContext).inflate( R.layout.item_weather_detail, parent,false);

        return new ViewHolder(view);
    }

    public void setDatas(Collection collection){
        forecastEntities.clear();
        if(collection!=null && !collection.isEmpty()){
            forecastEntities.addAll(collection);
        }
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Weather.DailyForecastEntity dailyForecastEntity = forecastEntities.get(position);
        if(dailyForecastEntity!=null){
            holder.tvDate.setText(dailyForecastEntity.date);
            holder.tvFan.setText(dailyForecastEntity.wind.dir);
            holder.tvDay.setText("早上");
            holder.imgDay.setImageDrawable(CityWeatherRecycleAdapter.getImgDrawable(mContext,Integer.valueOf(dailyForecastEntity.cond.codeD)));
            holder.tvNight.setText("晚上");
            holder.imgNight.setImageDrawable(CityWeatherRecycleAdapter.getImgDrawable(mContext,Integer.valueOf(dailyForecastEntity.cond.codeN)));

        }
    }

    @Override
    public int getItemCount() {
        return forecastEntities.size();
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
