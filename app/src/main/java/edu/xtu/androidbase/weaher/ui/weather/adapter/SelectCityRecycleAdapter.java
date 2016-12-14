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
import edu.xtu.androidbase.weaher.ui.weather.domain.Province;

/**
 * Created by huilin on 2016/12/5.
 */

public class SelectCityRecycleAdapter extends RecyclerView.Adapter<SelectCityRecycleAdapter.ViewHolder> {
    Context mContext;
    public static int CITY = 0;
    public static int PROVINCE = 1;
    public static int ZONE = 2;
    public int cityFlag = 0;
    public List<City> cities = new ArrayList<>();
    public List<Province> provinces = new ArrayList<>();
    private onClickListener onClickListener;

    public void setOnClickListener(SelectCityRecycleAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_select_city, parent, false);

        return new ViewHolder(inflate);
    }
    public void setData(Collection datas, int cityFlag) {
        this.cityFlag = cityFlag;
        cities.clear();
        provinces.clear();
        if (!datas.isEmpty()) {
            if(cityFlag == CITY){
                cities.addAll(datas);
            }else if(cityFlag == PROVINCE){
                provinces.addAll(datas);
            }
        }
        notifyDataSetChanged();
    }

    public void addData(Collection datas,int cityFlag){
        this.cityFlag = cityFlag;
        if(!datas.isEmpty()){
            if(cityFlag == CITY){
                cities.addAll(datas);
            }else if(cityFlag == PROVINCE){
                provinces.addAll(datas);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String name = "";
        if(cityFlag == CITY){
            City city = cities.get(position);
            name = city.getCityName();
        }else if(cityFlag == PROVINCE){
            Province province = provinces.get(position);
            name = province.getProName();

        }else if(cityFlag == ZONE){

        }
        holder.tvCity.setText(name);
        if(onClickListener!=null){

            onClickListener.onClickListener(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return cityFlag==CITY?cities.size():provinces.size();
    }

    static public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_city)
        public TextView tvCity;
        @Bind(R.id.card_view)
        public CardView cardView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface onClickListener{
        void onClickListener(ViewHolder viewHolder,int position);
    }
}
