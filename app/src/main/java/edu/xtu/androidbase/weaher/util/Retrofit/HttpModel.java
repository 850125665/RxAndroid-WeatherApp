package edu.xtu.androidbase.weaher.util.Retrofit;

import java.io.Serializable;

/**
 * Created by huilin on 2016/11/6.
 */
public class HttpModel implements Serializable {
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
