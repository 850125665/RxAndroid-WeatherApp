package edu.xtu.androidbase.weaher.ui.weather.fragment;

import android.view.View;

import edu.xtu.androidbase.weaher.R;
import edu.xtu.androidbase.weaher.ui.base.BaseFragment;
import edu.xtu.androidbase.weaher.util.view.LoadView;

/**
 * Created by huilin on 2016/11/12.
 */
public class CItyFragment extends BaseFragment {

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected int getResourceId() {
        return R.layout.fragment_city;
    }

    @Override
    protected void getNet(LoadView.IOnNetListener iOnNetListener) {
        iOnNetListener.getState(LoadView.LoadResult.success);
    }
}
