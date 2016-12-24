package edu.xtu.androidbase.weaher.ui.weather.model;

import java.util.List;
import java.util.concurrent.TimeUnit;

import edu.xtu.androidbase.weaher.ui.weather.domain.SelectCity;
import edu.xtu.androidbase.weaher.ui.weather.domain.SelectCityDao;
import edu.xtu.androidbase.weaher.util.DbManager;
import edu.xtu.androidbase.weaher.util.RxUtil.RxHelp;
import rx.Observable;

/**
 * Created by huilin on 2016/12/14.
 */

public class CityModel implements ICityModel {
    @Override
    public Observable<List<SelectCity>> showListCity(int pageIndex,int pageSize) {
        return DbManager.getInstant().mDaoSession.getSelectCityDao()
                .queryBuilder().where(SelectCityDao.Properties.Status.eq(1))
                .limit(pageSize)
                .offset(pageIndex*pageSize)
                .rx()
                .list()
                .delay(200, TimeUnit.MILLISECONDS)
                .compose(RxHelp.<List<SelectCity>>onlineSchedul());
    }
}
