package edu.xtu.androidbase.weaher.ui.weather.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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
public class RecycleAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Weather> weathers = new ArrayList<>();
    private OnClickListener onclickListenr;

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
        View view = View.inflate(mContext, R.layout.item_weather, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        ((ViewHolder) holder).cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onclickListenr != null) {

                    onclickListenr.OnClickItem();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.content_img)
        ImageView contentImg;
        @Bind(R.id.content_tv)
        TextView contentTv;
        @Bind(R.id.card_view)
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    public interface OnClickListener {
        void OnClickItem();
    }
}
