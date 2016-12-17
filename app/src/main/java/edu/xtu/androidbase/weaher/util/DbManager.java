package edu.xtu.androidbase.weaher.util;

import android.content.Context;
import android.os.Environment;

import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.query.QueryBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import edu.xtu.androidbase.weaher.ui.weather.domain.DaoMaster;
import edu.xtu.androidbase.weaher.ui.weather.domain.DaoSession;
import edu.xtu.androidbase.weaher.util.RxUtil.RxHelp;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by huilin on 2016/12/9.
 */

public class DbManager {

    private String TAG = this.getClass().getSimpleName();
    public static final String DB_PATH = "/data" + Environment.getDataDirectory().getAbsolutePath() + "/" + AppInfo.getAppInstant().getPacketName();
    private final int BUFFER_SIZE = 400000;
    public static final String CITY_DB = "china_city.db";
    public DaoSession mDaoSession;
    private DaoMaster daoMaster;
    private Context mContext;

    private DbManager() {

    }


    public static DbManager getInstant() {
        return DbManageHolder.INSTANT;
    }

    public static class DbManageHolder {
        private static DbManager INSTANT = new DbManager();
    }

    public Observable<DaoSession> openDb(final int rId, final String dbFile) {

        return Observable.create(new Observable.OnSubscribe<DaoSession>() {
            @Override
            public void call(Subscriber<? super DaoSession> subscriber) {
                File file = new File(DB_PATH + "/" + dbFile);
                if (file.exists()) {
//                    DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper
//                            (AppInfo.getAppInstant().getMyContext(), DbManager.DB_PATH + "/" + dbFile);
//                    DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
//                    getDaoMaster(DbManager.DB_PATH + "/" + dbFile).newSession();
                    subscriber.onNext( getDaoMaster(DbManager.DB_PATH + "/" + dbFile).newSession());
                } else {
                    InputStream inputStream = AppInfo.getAppInstant().getMyContext().getResources().openRawResource(rId);
                    try {
                        FileOutputStream fos = new FileOutputStream(file);
                        byte[] buffer = new byte[BUFFER_SIZE];
                        int count = 0;
                        while ((count = inputStream.read(buffer)) > 0) {
                            fos.write(buffer, 0, count);
                        }
                        fos.close();
                        inputStream.close();
//                        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper
//                                (AppInfo.getAppInstant().getMyContext(), DbManager.DB_PATH + "/" + dbFile);
//                        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
                        subscriber.onNext( getDaoMaster(DbManager.DB_PATH + "/" + dbFile).newSession());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }).compose(RxHelp.<DaoSession>onlineSchedul());

    }

    public void init(final int rId, final String dbFile,Context context) {
        this.mContext = context;
        openDb(rId, dbFile)
                .subscribe(new Subscriber<DaoSession>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(TAG, e.getMessage());
                    }

                    @Override
                    public void onNext(DaoSession daoSession) {
                        mDaoSession = daoSession;
                    }
                });
    }

    public void init(String dbName,Context mContext){
        this.mContext = mContext;
        this.mDaoSession = getDaoMaster(dbName).newSession();
    }

    public DaoMaster getDaoMaster(String dbName) {
        if(daoMaster==null){
            MyOpenHelper myOpenHelper = new MyOpenHelper(mContext,dbName);
//            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(mContext,dbName);
            myOpenHelper.onUpgrade(myOpenHelper.getWritableDb(),1,DaoMaster.SCHEMA_VERSION);

            daoMaster = new DaoMaster(myOpenHelper.getWritableDb());
        }
        return daoMaster;
    }
    public static void enableQueryBuilderLog(){
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }
}
