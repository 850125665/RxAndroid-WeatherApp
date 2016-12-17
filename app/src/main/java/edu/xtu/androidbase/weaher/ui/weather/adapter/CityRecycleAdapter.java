package edu.xtu.androidbase.weaher.ui.weather.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.xtu.androidbase.weaher.R;
import edu.xtu.androidbase.weaher.ui.weather.domain.City;
import edu.xtu.androidbase.weaher.ui.weather.domain.SelectCity;

/**
 * Created by huilin on 2016/12/5.
 */

public class CityRecycleAdapter extends RecyclerView.Adapter<CityRecycleAdapter.ViewHolder> {
    private Context mContext;


    public List<SelectCity> cities = new ArrayList<>();


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_city, parent, false);
        return new ViewHolder(inflate);
    }

    public void setDatas(Collection datas){
        cities.clear();
        if(!datas.isEmpty()){
            cities.addAll(datas);
        }
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvCity.setText(cities.get(position).getCityName());
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_city)
        TextView tvCity;
        @Bind(R.id.tv_temperature)
        TextView tvTemperature;
        @Bind(R.id.card_view)
        CardView cardView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
