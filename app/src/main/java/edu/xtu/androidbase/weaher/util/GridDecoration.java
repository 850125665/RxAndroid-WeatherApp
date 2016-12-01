package edu.xtu.androidbase.weaher.util;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import edu.xtu.androidbase.weaher.R;

/**
 * Created by huilin on 2016/11/25.
 */

public class GridDecoration extends RecyclerView.ItemDecoration {
    int margin = 0;

    public GridDecoration(Context context) {
        margin = context.getResources().getDimensionPixelSize(R.dimen.material_16dp);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int childAdapterPosition = parent.getChildAdapterPosition(view);
        if (childAdapterPosition > 0) {

            if (childAdapterPosition % 2 == 0) {
                outRect.set(margin / 2, margin, margin / 2, 0);
            } else {
                outRect.set(margin / 2, margin, margin / 2, 0);
            }
        } else {
        }

    }
}
