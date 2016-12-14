package edu.xtu.androidbase.weaher.ui.weather.model.usercase;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

import edu.xtu.androidbase.weaher.R;
import edu.xtu.androidbase.weaher.ui.weather.domain.City;
import edu.xtu.androidbase.weaher.ui.weather.domain.CityDao;
import edu.xtu.androidbase.weaher.ui.weather.domain.DaoMaster;
import edu.xtu.androidbase.weaher.ui.weather.domain.DaoSession;
import edu.xtu.androidbase.weaher.ui.weather.model.request.CityRequest;
import edu.xtu.androidbase.weaher.util.AppInfo;
import edu.xtu.androidbase.weaher.util.DbManager;
import edu.xtu.androidbase.weaher.util.RxUtil.RxHelp;
import edu.xtu.androidbase.weaher.util.RxUtil.UseCase;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by huilin on 2016/12/12.
 */

public class CityUseCase extends UseCase<List<City>, CityRequest> {
    @Override
    public Observable<List<City>> createObservable(final CityRequest cityRequest) {
//        Observable<DaoSession> daoSessionObservable = DbManager.getInstant().openDb(R.raw.china_city, DbManager.CITY_DB);
//        return daoSessionObservable.concatMap(new Func1<DaoSession, Observable<List<City>>>() {
//            @Override
//            public Observable<List<City>> call(DaoSession daoSession) {
//                return selectCity(cityRequest.getCity(), daoSession, cityRequest.getWhereCondition());
//            }
//        });
        return selectCity(cityRequest.getCity(),DbManager.getInstant().mDaoSession,cityRequest.getWhereCondition());
    }

    public Observable<List<City>> selectCity(final City city, final DaoSession daoSession, final WhereCondition whereCondition) {

        return daoSession.getCityDao().queryBuilder().where(whereCondition).rx().list().compose(RxHelp.<List<City>>onlineSchedul());

//        return Observable.create(new Observable.OnSubscribe<List<City>>() {
//            @Override
//            public void call(Subscriber<? super List<City>> subscriber) {
//                List<City> list = daoSession.getCityDao().queryBuilder().where(whereCondition).build().list();
//                subscriber.onNext(list);
//                subscriber.onCompleted();
//
//            }
//        }).compose(RxHelp.<List<City>>onlineSchedul());
    }
}
