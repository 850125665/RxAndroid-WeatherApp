package edu.xtu.androidbase.weaher.ui.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.xtu.androidbase.weaher.R;
import edu.xtu.androidbase.weaher.ui.base.HomeActivity;

/**
 * Created by huilin on 2016/8/15.
 */
public class TestHomeActivity extends HomeActivity {

    List<HomeTab> homeTabList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected List<HomeTab> getHomeTabList() {

        if(homeTabList.isEmpty()){
            HomeTab one = new HomeTab();
            one.setTab("one");
            one.setView(getIndicatorView("one"));
            one.setClassz(Fragment1.class);
            homeTabList.add(one);

            HomeTab two = new HomeTab();
            two.setTab("two");
            two.setView(getIndicatorView("two"));
            two.setClassz(Fragment2.class);
            homeTabList.add(two);

            HomeTab three = new HomeTab();
            three.setTab("three");
            three.setView(getIndicatorView("three"));
            three.setClassz(Fragment3.class);
            homeTabList.add(three);

            HomeTab four = new HomeTab();
            four.setTab("four");
            four.setView(getIndicatorView("four"));
            four.setClassz(Fragment4.class);
            homeTabList.add(four);
        }

        return homeTabList;
    }

    private View getIndicatorView(String one) {
        View view = View.inflate(mContext,R.layout.tab_item_view,null);
        TextView tv = (TextView) view.findViewById(R.id.textview);
        ImageView img = (ImageView) view.findViewById(R.id.imageview);
        tv.setText(one);

        return view;
    }

    @Override
    public int getContenViewId() {
        return R.layout.activity_test_home;
    }


}
