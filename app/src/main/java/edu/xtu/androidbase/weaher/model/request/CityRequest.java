package edu.xtu.androidbase.weaher.model.request;

import org.greenrobot.greendao.query.WhereCondition;

import edu.xtu.androidbase.weaher.domain.City;

/**
 * Created by huilin on 2016/12/12.
 */

public class CityRequest {
    private City city;
    private String sql;
    private WhereCondition whereCondition;
    public CityRequest(){


    }
    public WhereCondition getWhereCondition() {
        return whereCondition;
    }

    public void setWhereCondition(WhereCondition whereCondition) {
        this.whereCondition = whereCondition;
    }



    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
