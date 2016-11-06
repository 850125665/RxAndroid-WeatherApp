package edu.xtu.androidbase.weaher.util.volley;

import com.android.volley.VolleyError;

/**
 * Created by skyar@live.cn.If you have any questions please contact me!
 */
public interface ApiRequestListener<T> {

    void onSuccessResponse(T response);

    void onErrorResponse(VolleyError error);
}