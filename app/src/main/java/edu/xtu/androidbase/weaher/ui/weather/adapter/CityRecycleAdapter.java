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
import edu.xtu.androidbase.weaher.ui.weather.domain.SelectCity;
import edu.xtu.androidbase.weaher.util.BaseAdapter;

/**
 * Created by huilin on 2016/12/5.
 */

public class CityRecycleAdapter extends BaseAdapter {
    private Context mContext;


    public List<SelectCity> cities = new ArrayList<>();



    @Override
    protected void onBindContentView(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if(itemViewType == COMMON_TYPE){
            ViewHolder cityHolder = (ViewHolder) holder;
            cityHolder.tvCity.setText(cities.get(position).getCityName());
        }
    }


    @Override
    public  RecyclerView.ViewHolder getCommonViewHolder(ViewGroup viewGroup, int viewType) {
        if(COMMON_TYPE == viewType){
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_city, viewGroup, false);
            return new ViewHolder(inflate);
        }

        return null;
    }

    public void setDatas(Collection datas){
        cities.clear();
        if(!datas.isEmpty()){
            cities.addAll(datas);
        }
        notifyDataSetChanged();
    }

    public void addDatas(Collection datas){
        if(!datas.isEmpty()){
            cities.addAll(datas);
        }
        notifyDataSetChanged();
    }




    @Override
    public int getCount() {
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
