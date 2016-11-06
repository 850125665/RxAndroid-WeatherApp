package edu.xtu.androidbase.weaher.util.Retrofit;

/**
 * Created by huilin on 2016/11/6.
 */
public class HttpError extends Throwable {

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
