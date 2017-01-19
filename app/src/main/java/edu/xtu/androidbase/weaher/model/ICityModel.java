package edu.xtu.androidbase.weaher.model;

import java.util.List;

import edu.xtu.androidbase.weaher.domain.SelectCity;
import rx.Observable;

/**
 * Created by huilin on 2016/12/14.
 */

public interface ICityModel {
    public Observable<List<SelectCity>> showListCity(int pageIndex,int pageSize);
}
