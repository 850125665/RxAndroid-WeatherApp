package edu.xtu.androidbase.weaher.ui.weather.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by huilin on 2016/12/13.
 */
@Entity(nameInDb = "T_Zone",createInDb = false)
public class Zone {
    @Id
    @Property(nameInDb = "ZoneID")
    private int zoneId;

    @Property(nameInDb = "ZoneName")
    private String zoneName;

    @Property(nameInDb = "CityID")
    private String cityId;

    public String getCityId() {
        return this.cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getZoneName() {
        return this.zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public int getZoneId() {
        return this.zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    @Generated(hash = 1111369283)
    public Zone(int zoneId, String zoneName, String cityId) {
        this.zoneId = zoneId;
        this.zoneName = zoneName;
        this.cityId = cityId;
    }

    @Generated(hash = 1333518924)
    public Zone() {
    }
}
