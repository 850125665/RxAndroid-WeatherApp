package edu.xtu.androidbase.weaher.ui.weather.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.xtu.androidbase.weaher.R;

/**
 * Created by huilin on 2016/12/5.
 */

public class CityRecycleAdapter extends RecyclerView.Adapter<CityRecycleAdapter.ViewHolder> {
    private Context mContext;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_city, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
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
