package edu.xtu.androidbase.weaher.util.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by skyar@live.cn.If you have any questions please contact me!
 */
public class VolleyRequest extends Request<JSONObject> {
    private Map<String, Object> mParams;
    private Response.Listener<JSONObject> mListener;
    private Map<String, String> mHeader = new HashMap<String, String>();

    public VolleyRequest(int method, String url, Map<String, Object> params,
                         Response.Listener<JSONObject> listener,
                         Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mListener = listener;
        this.mParams = params;
    }

    public Response.Listener<JSONObject> getListener() {
        return mListener;
    }

//    @Override
//    public Map<String, String> getHeaders() throws AuthFailureError {
//        mHeader.put("Cookie", UserInfo.getInstance().getCookie());
//        return mHeader;
//    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return mParams != null && mParams.size() > 0 ?
                this.encodeParameters(mParams, this.getParamsEncoding()) :
                null;
    }

    @Override
    public String getBodyContentType() {
        return "application/x-www-form-urlencoded; charset=" + this
                .getParamsEncoding();
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(
            NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));

//            extractSessionID(response.headers.toString());

            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        mListener.onResponse(response);
    }

    private byte[] encodeParameters(Map<String, Object> params,
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

            return encodedParams.toString().getBytes(paramsEncoding);
        } catch (UnsupportedEncodingException var6) {
            throw new RuntimeException(
                    "Encoding not supported: " + paramsEncoding, var6);
        }
    }

    /**
     * 提取session id
     *
     * @param header http头
     */
//    private void extractSessionID(String header) {
//        if (TextUtils.isEmpty(UserInfo.getInstance().getCookie())) {
//            //使用正则表达式从response的头中提取cookie内容的子串
//            Pattern pattern = Pattern.compile("Set-Cookie.*?;");
//            Matcher m = pattern.matcher(header);
//            if (m.find()) {
//                String cookieFromResponse = m.group();
//
//                //去掉cookie末尾的分号
//                cookieFromResponse = cookieFromResponse.substring(11, cookieFromResponse.length() - 1);
//
//                UserInfo.getInstance().setCookie(cookieFromResponse);
//            }
//        }
//    }
}

