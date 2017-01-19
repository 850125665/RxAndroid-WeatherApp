package edu.xtu.androidbase.weaher.ui.weather.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.xtu.androidbase.weaher.R;
import edu.xtu.androidbase.weaher.ui.base.BaseActivity;
import edu.xtu.androidbase.weaher.ui.base.TerminalToolBarActivity;
import edu.xtu.androidbase.weaher.adapter.HomeViewPageAdapter;
import edu.xtu.androidbase.weaher.ui.weather.fragment.CityFragment;
import edu.xtu.androidbase.weaher.ui.weather.fragment.MainFragment;
import edu.xtu.androidbase.weaher.ui.weather.fragment.SelectSelectCityFragment;
import edu.xtu.androidbase.weaher.presenter.HomePresenter;
import edu.xtu.androidbase.weaher.ui.weather.view.IHomeView;
import edu.xtu.androidbase.weaher.util.AppMethods;

/**
 * Created by huilin on 2016/11/6.
 */
public class HomeActivity extends BaseActivity implements IHomeView, NavigationView.OnNavigationItemSelectedListener {

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
    @Bind(R.id.nav_view)
    NavigationView navView;
    public long backTime;
    private HomePresenter homePresenter;
    private HomeViewPageAdapter homeViewPageAdapter;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    private ImageView imageView;
    private ViewGroup decorView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setWindowAnimations(R.style.activity_anim_alpha);
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
        initDrawer();
        initViewPage();
        decorView = (ViewGroup) getWindow().getDecorView();
        final ViewTreeObserver viewTreeObserver = decorView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                imageView = new ImageView(mContext);
                imageView.setImageResource(R.color.colorPrimary);
                imageView.setVisibility(View.GONE);
                decorView.addView(imageView, decorView.getWidth(), decorView.getHeight());
                decorView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

    }

    private void initViewPage() {

        MainFragment mainFragment = new MainFragment();
        fragments.add(mainFragment);
        titles.add("天气");
        CityFragment cityFragment = new CityFragment();
        fragments.add(cityFragment);
//        CityTestFragment cityTestFragment = new CityTestFragment();
//        fragments.add(cityTestFragment);
        titles.add("城市");

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText("天气"));
        tabLayout.addTab(tabLayout.newTab().setText("城市"));

        homeViewPageAdapter = new HomeViewPageAdapter(getSupportFragmentManager());
        homeViewPageAdapter.setDatas(fragments, titles);

        viewPager.setAdapter(homeViewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                fab.animate().translationY(0).start();


                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position == 0) {

                        } else {
                            circularShow();
                        }
                    }

                });


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initDrawer() {
        if (navView != null) {
            View headerView = navView.inflateHeaderView(R.layout.view_nav_head);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
            navView.setNavigationItemSelectedListener(this);

        }
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


    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.nav_city:
                TerminalToolBarActivity.show(mContext, SelectSelectCityFragment.class, null);
                break;
        }


        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Date date = new Date();
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (date.getTime() - backTime <= 2000) {
                return super.onKeyDown(keyCode, event);
            }
            backTime = date.getTime();
            AppMethods.shwoToast("双击返回键退出");
        } else {
            return super.onKeyDown(keyCode, event);
        }
        return false;

    }

    private void circularShow() {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            int loaction[] = new int[2];
            imageView.setVisibility(View.VISIBLE);
            fab.getLocationInWindow(loaction);
            int cx = loaction[0] + fab.getWidth() / 2;
            int cy = loaction[1] + fab.getHeight() / 2;
            float finalRadius = (float) Math.hypot(decorView.getWidth(), decorView.getHeight());
            Animator circularReveal = null;
            circularReveal = ViewAnimationUtils.createCircularReveal(imageView, cx, cy, 0, finalRadius);
            circularReveal.setDuration(1000);
            circularReveal.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    TerminalToolBarActivity.showForResult(mContext, SelectSelectCityFragment.class, null,100);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
            });
            circularReveal.start();
        } else {
            TerminalToolBarActivity.show(mContext, SelectSelectCityFragment.class, null);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }

    }

    private void circularReturn() {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            int loaction[] = new int[2];
            fab.getLocationInWindow(loaction);
            int cx = loaction[0] + fab.getWidth() / 2;
            int cy = loaction[1] + fab.getHeight() / 2;
            float finalRadius = (float) Math.hypot(decorView.getWidth(), decorView.getHeight());
            Animator circularReveal = null;
            circularReveal = ViewAnimationUtils.createCircularReveal(imageView, cx, cy, finalRadius, 0);
            circularReveal.setDuration(1000);
            circularReveal.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    imageView.setVisibility(View.GONE);
                }
            });
            circularReveal.start();
        } else {
            imageView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (data != null) {
                int circularReturn = data.getIntExtra("circular", 0);
                if (circularReturn == 100) {
                    circularReturn();
                }
            }
        }
    }
}
