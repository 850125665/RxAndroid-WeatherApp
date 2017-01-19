package edu.xtu.androidbase.weaher.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;

import edu.xtu.androidbase.weaher.domain.DaoMaster;
import edu.xtu.androidbase.weaher.domain.SelectCityDao;

/**
 * Created by huilin on 2016/12/14.
 */

public class MyOpenHelper extends DaoMaster.DevOpenHelper {

    public MyOpenHelper(Context context, String name) {
        super(context, name);
    }

    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        if(oldVersion!=newVersion){
            SelectCityDao.createTable(db,true);
            try {
                db.execSQL("alter table SELECT_CITY add status int");
            }catch (Exception e){

            }
        }
    }
}
