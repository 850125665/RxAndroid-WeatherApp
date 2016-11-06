package edu.xtu.androidbase.weaher.util.volley;

import com.android.volley.Request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import edu.xtu.androidbase.weaher.util.LogUtils;

/**
 * Created by skyar@live.cn.If you have any questions please contact me!
 */
public class ApiRequest {

    private int mRequestType;
    private String mRequestUrl;
    private Map<String, Object> mParams;
    private byte[] mBinaryData;
    private int mBinaryType;
    private Class mClazz;
    private String mGsonParseKey;
    private ApiRequestListener mApiRequestListener;
    /**
     * 无参数、无gson解析接口
     *
     * @param url
     * @param type
     * @param listener
     */
    public ApiRequest(String url, int type, ApiRequestListener listener) {
        this(url, type, null, null, listener);
    }

    /**
     * 无参数、带gson解析接口(如果datakey为null，就以接收到的整个Json进行解析，否则以dataKey对应的value进行解析)
     *
     * @param url
     * @param type
     * @param dataClass
     * @param dataKey
     * @param listener
     */
    public ApiRequest(String url, int type, Class dataClass, String dataKey,
            ApiRequestListener listener) {
        this(url, type, null, dataClass, dataKey, listener);
    }

    /**
     * 带参数、无gson解析接口
     *
     * @param url
     * @param type
     * @param params
     * @param listener
     */
    public ApiRequest(String url, int type, Map<String, Object> params,
            ApiRequestListener listener) {
        this(url, type, params, null, null, listener);
    }

    /**
     * 带参数、带gson解析接口
     *
     * @param url
     * @param type
     * @param params
     * @param dataClass
     * @param dataKey
     * @param listener
     */
    public ApiRequest(String url, int type, Map<String, Object> params,
            Class dataClass, String dataKey, ApiRequestListener listener) {
        this.mRequestUrl = url;
        this.mRequestType = type;
        this.mParams = params;
        this.mClazz = dataClass;
        this.mGsonParseKey = dataKey;
        this.mApiRequestListener = listener;
    }

    /**
     * 带参数、无gson解析接口、有二进制数据
     *
     * @param url
     * @param type
     * @param params
     * @param binaryData
     * @param binaryType
     * @param listener
     */
    public ApiRequest(String url, int type, Map<String, Object> params,
            byte[] binaryData, int binaryType, ApiRequestListener listener) {
        this(url, type, params, binaryData, binaryType, null, null, listener);
    }

    /**
     * 带参数、带gson解析接口、有二进制数据
     *
     * @param url
     * @param type
     * @param params
     * @param binaryData
     * @param binaryType
     * @param dataClass
     * @param dataKey
     * @param listener
     */
    public ApiRequest(String url, int type, Map<String, Object> params,
            byte[] binaryData, int binaryType, Class dataClass, String dataKey,
            ApiRequestListener listener) {
        this.mRequestUrl = url;
        this.mRequestType = type;
        this.mParams = params;
        this.mBinaryData = binaryData;
        this.mBinaryType = binaryType;
        this.mClazz = dataClass;
        this.mGsonParseKey = dataKey;
        this.mApiRequestListener = listener;
    }

    public Class getDataClass() {
        return mClazz;
    }

    public ApiRequestListener getApiRequestListener() {
        return mApiRequestListener;
    }

    public String getGsonKey() {
        return mGsonParseKey;
    }

    public Request getRequest() {
        int method = Request.Method.GET;
        if (mRequestType == Method.POST
                || mRequestType == Method.POST_BIN_FILE) {
            method = Request.Method.POST;
        }

        if (method == Request.Method.GET && mParams != null
                && mParams.size() > 0) {
            String parameters = encodeParameters(mParams, "utf-8");
            mRequestUrl += "?" + parameters;
        }

        switch (mRequestType) {
            case Method.GET:
                LogUtils.d("volley_request", "GET " + mRequestUrl);
                break;
            case Method.POST:
                if (mParams != null && mParams.size() > 0) {
                    String parameters = getSimpleParams(mParams, "utf-8");
                    LogUtils.d("volley_request",
                            "POST " + mRequestUrl + "?" + parameters);
                } else {
                    LogUtils.d("volley_request", "POST " + mRequestUrl);
                }
                break;
            case Method.POST_BIN_FILE:
                LogUtils.d("volley_request", "POST " + mRequestUrl + " binary file");
                break;
        }

        if (mRequestType == Method.POST_BIN_FILE) {
            return new VolleyUploadBinaryRequest(method, mRequestUrl, mParams,
                    mBinaryData, mBinaryType, new VolleyListenerImpl(this),
                    new VolleyErrorListenerImpl(this));
        } else {
            return new VolleyRequest(method, mRequestUrl, mParams,
                    new VolleyListenerImpl(this),
                    new VolleyErrorListenerImpl(this));
        }
    }

    private String encodeParameters(Map<String, Object> params,
            String paramsEncoding) {
        StringBuilder encodedParams = new StringBuilder();

        try {
            Iterator uee = params.entrySet().iterator();

            while (uee.hasNext()) {
                Map.Entry entry = (Map.Entry) uee.next();
                encodedParams.append(URLEncoder
                        .encode((String) entry.getKey(), paramsEncoding));
                encodedParams.append('=');
                encodedParams.append(URLEncoder
                        .encode(String.valueOf(entry.getValue()),
                                paramsEncoding));
                encodedParams.append('&');
            }

            return encodedParams.substring(0, encodedParams.length() - 1)
                    .toString();
        } catch (UnsupportedEncodingException var6) {
            throw new RuntimeException(
                    "Encoding not supported: " + paramsEncoding, var6);
        }
    }

    private String getSimpleParams(Map<String, Object> params,
                                    String paramsEncoding) {
        StringBuilder encodedParams = new StringBuilder();

        try {
            Iterator uee = params.entrySet().iterator();

            while (uee.hasNext()) {
                Map.Entry entry = (Map.Entry) uee.next();
                if (isUserInputParam((String) entry.getKey())) {
                    encodedParams.append(URLEncoder
                            .encode((String) entry.getKey(), paramsEncoding));
                    encodedParams.append('=');
                    encodedParams.append(URLEncoder
                            .encode(String.valueOf(entry.getValue()),
                                    paramsEncoding));
                    encodedParams.append('&');
                }
            }

            return encodedParams.substring(0, encodedParams.length() - 1)
                    .toString();
        } catch (UnsupportedEncodingException var6) {
            throw new RuntimeException(
                    "Encoding not supported: " + paramsEncoding, var6);
        }
    }

    private boolean isUserInputParam(String key) {
        return !("mac".equals(key)
                ||"operator".equals(key)
                ||"phoneModel".equals(key)
                ||"app_name".equals(key)
                ||"os_version".equals(key)
                ||"os_type".equals(key)
                ||"net_type".equals(key)
                ||"fr".equals(key)
                ||"app_version_name".equals(key)
                ||"app_version_code".equals(key));
    }

    public interface Method {
        int GET = 0;
        int POST = 1;
        int POST_BIN_FILE = 2;
    }
}
