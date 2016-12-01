package edu.xtu.androidbase.weaher.util;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import edu.xtu.androidbase.weaher.R;

/**
 * Created by huilin on 2016/12/1.
 */

public class LineDecoration extends RecyclerView.ItemDecoration {
    private int margin = 0;
    public LineDecoration(Context context) {
        this.margin = context.getResources().getDimensionPixelSize(R.dimen.material_8dp);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(margin,margin,margin,0);
    }
}
