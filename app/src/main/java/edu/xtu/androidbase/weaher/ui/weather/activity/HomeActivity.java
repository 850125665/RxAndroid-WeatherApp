package edu.xtu.androidbase.weaher.ui.weather.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.xtu.androidbase.weaher.R;
import edu.xtu.androidbase.weaher.ui.base.BaseActivity;
import edu.xtu.androidbase.weaher.ui.weather.presenter.HomePresenter;
import edu.xtu.androidbase.weaher.ui.weather.view.IHomeView;
import edu.xtu.androidbase.weaher.util.AppInfo;
import edu.xtu.androidbase.weaher.util.AppMethods;

/**
 * Created by huilin on 2016/11/6.
 */
public class HomeActivity extends BaseActivity implements IHomeView {

    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initView();
        homePresenter = new HomePresenter(this);
        initData();
    }

    private void initData() {
        homePresenter.setCityName();
    }

    private void initView() {
        setSupportActionBar(toolbar);
    }

    @Override
    public void setCityName(String cityName) {
        toolbar.setTitle(cityName);
    }

    @Override
    public void error(String meg) {
        AppMethods.shwoToast(meg);
    }

    @Override
    public void success() {

    }
}
