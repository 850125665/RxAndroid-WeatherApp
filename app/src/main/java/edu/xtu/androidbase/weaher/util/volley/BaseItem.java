package edu.xtu.androidbase.weaher.util.volley;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by skyar@live.cn.If you have any questions please contact me!
 */
public class BaseItem implements Serializable {

    public String toJson() {
        return new Gson().toJson(this);
    }
}
