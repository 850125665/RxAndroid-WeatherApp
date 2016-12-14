package edu.xtu.androidbase.weaher.ui.weather.model.request;

import org.greenrobot.greendao.query.WhereCondition;

import edu.xtu.androidbase.weaher.ui.weather.domain.Province;

/**
 * Created by huilin on 2016/12/13.
 */

public class ProvinceRequest {
    private Province province;
    private WhereCondition whereCondition;
    private String sql;

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public WhereCondition getWhereCondition() {
        return whereCondition;
    }

    public void setWhereCondition(WhereCondition whereCondition) {
        this.whereCondition = whereCondition;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
