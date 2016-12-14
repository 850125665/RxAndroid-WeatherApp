package edu.xtu.androidbase.weaher.ui.weather.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by huilin on 2016/12/13.
 */
@Entity(nameInDb = "T_Province" , createInDb = false)
public class Province {

    @Id
    @Property(nameInDb = "ProName")
    private String proName;

    @Property(nameInDb = "ProSort")
    private String proSort;

    @Property(nameInDb = "ProRemark")
    private String proRemark;

    public String getProRemark() {
        return this.proRemark;
    }

    public void setProRemark(String proRemark) {
        this.proRemark = proRemark;
    }

    public String getProSort() {
        return this.proSort;
    }

    public void setProSort(String proSort) {
        this.proSort = proSort;
    }

    public String getProName() {
        return this.proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    @Generated(hash = 1072464418)
    public Province(String proName, String proSort, String proRemark) {
        this.proName = proName;
        this.proSort = proSort;
        this.proRemark = proRemark;
    }

    @Generated(hash = 1309009906)
    public Province() {
    }

}
