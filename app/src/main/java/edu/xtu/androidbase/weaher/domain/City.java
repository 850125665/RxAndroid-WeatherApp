package edu.xtu.androidbase.weaher.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by huilin on 2016/12/12.
 */
@Entity(nameInDb = "T_City",createInDb=false)
public class City  {


    @Id
    @Property(nameInDb = "citySort")
    private String citySort;
    @Property(nameInDb = "cityName")
    private String cityName;
    @Property(nameInDb = "proID")
    private String proID;

    public String getProID() {
        return this.proID;
    }

    public void setProID(String ProID) {
        this.proID = ProID;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String CityName) {
        this.cityName = CityName;
    }

    public String getCitySort() {
        return this.citySort;
    }

    public void setCitySort(String CitySort) {
        this.citySort = CitySort;
    }

    @Generated(hash = 477266118)
    public City(String citySort, String cityName, String proID) {
        this.citySort = citySort;
        this.cityName = cityName;
        this.proID = proID;
    }

    @Generated(hash = 750791287)
    public City() {
    }

    @Override
    public String toString() {
        return "City{" +
                "citySort='" + citySort + '\'' +
                ", cityName='" + cityName + '\'' +
                ", proID='" + proID + '\'' +
                '}';
    }
}
