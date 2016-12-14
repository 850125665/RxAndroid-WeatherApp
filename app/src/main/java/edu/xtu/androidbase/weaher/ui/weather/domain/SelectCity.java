package edu.xtu.androidbase.weaher.ui.weather.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by huilin on 2016/12/14.
 */
@Entity
public class SelectCity {
    @Id(autoincrement = true)
    private long id;

    @Property
    private long cityId;
    
    @Property
    private String cityName;

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public long getCityId() {
        return this.cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Generated(hash = 2042066145)
    public SelectCity(long id, long cityId, String cityName) {
        this.id = id;
        this.cityId = cityId;
        this.cityName = cityName;
    }

    @Generated(hash = 58876853)
    public SelectCity() {
    }



}
