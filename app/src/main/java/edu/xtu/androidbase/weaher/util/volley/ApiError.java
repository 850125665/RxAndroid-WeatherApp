package edu.xtu.androidbase.weaher.util.volley;

import com.android.volley.VolleyError;

/**
 * Created by skyar@live.cn.If you have any questions please contact me!
 */
public class ApiError extends VolleyError {
    private int errorCode;
    private String message;

    public ApiError() {
        super();
    }

    public ApiError(int errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public ApiError(int errorCode, String message) {
        super();
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
