package edu.xtu.androidbase.weaher.util.volley;

import com.android.volley.NetworkError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import edu.xtu.androidbase.weaher.util.AppInfo;
import edu.xtu.androidbase.weaher.util.LogUtils;


/**
 * Created by skyar@live.cn.If you have any questions please contact me!
 */
public class VolleyErrorListenerImpl implements Response.ErrorListener {

    private ApiRequest mApiRequest;

    public VolleyErrorListenerImpl(ApiRequest apiRequest) {
        this.mApiRequest = apiRequest;
    }

    @Override
    public void onErrorResponse(final VolleyError error) {
        LogUtils.d("volley_response", "error " + error.getMessage());
        if (mApiRequest != null
                && mApiRequest.getApiRequestListener() != null) {
            if (error instanceof ServerError || error instanceof TimeoutError) {
                AppInfo.getAppInstant().getUiHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        ToastUtils.getInstance().showToast(false, "服务器出了点问题，请稍后再试！");
                    }
                }, 100);
            } else if (error instanceof NetworkError) {
                AppInfo.getAppInstant().getUiHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        ToastUtils.getInstance().showToast(false, "请检查您的网络连接设置！");
                    }
                }, 100);
            } else {
                AppInfo.getAppInstant().getUiHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        ToastUtils.getInstance().showToast(false, error.getMessage());
                    }
                }, 100);
            }
            mApiRequest.getApiRequestListener().onErrorResponse(error);
        }
    }
}
