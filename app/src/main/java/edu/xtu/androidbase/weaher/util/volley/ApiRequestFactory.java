package edu.xtu.androidbase.weaher.util.volley;

import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;

import org.json.JSONObject;

import java.util.Hashtable;

import edu.xtu.androidbase.weaher.BuildConfig;
import edu.xtu.androidbase.weaher.util.AppInfo;
import edu.xtu.androidbase.weaher.util.DeviceUtils;


/**
 * Created by skyar@live.cn.If you have any questions please contact me!
 */
public class ApiRequestFactory {

    private final static String HOST_ONLINE = "http://client.chemanman.com/shipper.php";
    public final static String HOST = BuildConfig.IS_TEST_SWITCH ?
            BuildConfig.HOST_OFFLINE : HOST_ONLINE;
    public final static String imgUrl="http://172.16.78.161/gcl";
    static Hashtable<String, Object> buildCommonParams() {
        Hashtable<String, Object> params = new Hashtable<String, Object>();

        params.put("mac", TextUtils.isEmpty(AppInfo.getAppInstant().getMac()) ? "" : AppInfo.getAppInstant().getMac());
        params.put("operator", TextUtils.isEmpty(AppInfo.getAppInstant().getOprator()) ? "" : AppInfo.getAppInstant().getOprator());
        params.put("phoneModel", DeviceUtils.getInstance().getModel());
//        params.put("session_id", UserInfo.getInstance().getSession());
        params.put("app_name", "fahuo");
        params.put("os_type", "android");
        params.put("os_version", DeviceUtils.getInstance().getSystemVersion());
        params.put("device_id", TextUtils.isEmpty(AppInfo.getAppInstant().getIMEI()) ? "" : AppInfo.getAppInstant().getIMEI());
//        params.put("net_type", DeviceUtils.getInstance().getNetType());
        params.put("fr", "app");
        params.put("app_version_name", AppInfo.getAppInstant().getVersionName());
        params.put("app_version_code", AppInfo.getAppInstant().getVersionCode());
        return params;
    }

    static void sendRequest(ApiRequest request, Object tag) {
        sendRequest(request, tag, 3000);
    }

    static void sendRequest(ApiRequest request, Object tag, int timeoutMs) {
        Request rq = request.getRequest();
        rq.setRetryPolicy(new DefaultRetryPolicy(timeoutMs, 0, 1.0f));
        RequestManager.addRequest(rq, tag);
    }

    static ApiRequest buildPostRequest(String path,
                                       Hashtable<String, Object> params, ApiRequestListener listener) {
        return buildPostRequest(path, params, null, listener);
    }

    static ApiRequest buildPostRequest(String path,
                                       Hashtable<String, Object> params, Class dataClass,
                                       ApiRequestListener listener) {
        return buildPostRequest(path, params, dataClass, "data", listener);
    }

    static ApiRequest buildPostRequest(String path,
                                       Hashtable<String, Object> params, Class dataClass, String key,
                                       ApiRequestListener listener) {
        String url = HOST + path;
        return new ApiRequest(url, ApiRequest.Method.POST, params, dataClass,
                key, listener);
    }

    private static void sendFakeRequest(ApiRequest apiRequest, JSONObject jsonObject) {
        VolleyListenerImpl volleyListener = new VolleyListenerImpl(apiRequest);
        volleyListener.onResponse(jsonObject);
    }

    public static void regist(Object tag, String phone, String password,String name, String status,
                             ApiRequestListener apiRequestListener) {
        Hashtable<String, Object> map = buildCommonParams();
        map.put("telephone", phone);
        map.put("pwd", password);
        map.put("status",status);
        map.put("name",name);
        ApiRequest request = buildPostRequest("/user/regist", map, apiRequestListener);
        sendRequest(request, tag);

    }


}
