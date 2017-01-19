package edu.xtu.androidbase.weaher.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by huilin on 2016/12/14.
 */
@Entity(nameInDb = "SELECT_CITY")
public class SelectCity {
    @Id(autoincrement = true)
    private Long id;

    @Property
    private long cityId;
    
    @Property
    private String cityName;

    @Property
    private int status = 0;

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCityName() {
        return this.cityName.substring(0,cityName.length());
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 599639398)
    public SelectCity(Long id, long cityId, String cityName, int status) {
        this.id = id;
        this.cityId = cityId;
        this.cityName = cityName;
        this.status = status;
    }

    @Generated(hash = 58876853)
    public SelectCity() {
    }


    @Override
    public String toString() {
        return "SelectCity{" +
                "id=" + id +
                ", cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", status=" + status +
                '}';
    }
}
