package edu.xtu.androidbase.weaher.model.usercase;

import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

import edu.xtu.androidbase.weaher.domain.DaoSession;
import edu.xtu.androidbase.weaher.domain.Province;
import edu.xtu.androidbase.weaher.model.request.ProvinceRequest;
import edu.xtu.androidbase.weaher.util.DbManager;
import edu.xtu.androidbase.weaher.util.RxUtil.RxHelp;
import edu.xtu.androidbase.weaher.util.RxUtil.UseCase;
import rx.Observable;

/**
 * Created by huilin on 2016/12/13.
 */

public class ProvinceUseCase extends UseCase<List<Province>,ProvinceRequest> {
    @Override
    public Observable<List<Province>> createObservable(final ProvinceRequest provinceRequest) {
//        return DbManager.getInstant().openDb(R.raw.china_city,DbManager.CITY_DB)
//                .concatMap(new Func1<DaoSession, Observable<List<Province>>>() {
//                    @Override
//                    public Observable<List<Province>> call(DaoSession daoSession) {
//                        return selectProvince(daoSession,provinceRequest.getWhereCondition());
//                    }
//                });
        return selectProvince(DbManager.getInstant().mDaoSession,provinceRequest.getWhereCondition());
    }
    public Observable<List<Province>> selectProvince(final DaoSession daoSession, final WhereCondition whereCondition) {
        return daoSession.getProvinceDao().rx().loadAll().compose(RxHelp.<List<Province>>onlineSchedul());
//        return Observable.create(new Observable.OnSubscribe<List<Province>>() {
//            @Override
//            public void call(Subscriber<? super List<Province>> subscriber) {
//                List<Province> list = daoSession.getProvinceDao().queryBuilder().where(whereCondition).build().list();
//                subscriber.onNext(list);
//                subscriber.onCompleted();
//
//            }
//        }).compose(RxHelp.<List<Province>>onlineSchedul());
    }
}
