package edu.xtu.androidbase.weaher.util.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;


/**
 * Created by skyar@live.cn.If you have any questions please contact me!
 */
public class VolleyUploadBinaryRequest extends Request<JSONObject> {

    private final static String BOUNDARY = "FlPm4LpSXsE";
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss_",
            Locale.getDefault());

    private Map<String, Object> mParams;
    private Response.Listener<JSONObject> mListener;
    private int mBinaryType;
    private byte[] mBinaryData;
    private Map<String, String> mHeader = new HashMap<String, String>();

    public VolleyUploadBinaryRequest(int method, String url,
            Map<String, Object> params, byte[] binaryData, int binaryType,
            Response.Listener<JSONObject> listener,
            Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mListener = listener;
        this.mParams = params;
        this.mBinaryType = binaryType;
        this.mBinaryData = binaryData;
    }

//    @Override
//    public Map<String, String> getHeaders() throws AuthFailureError {
//        mHeader.put("Cookie", UserInfo.getInstance().getCookie());
//        return mHeader;
//    }
    @Override
    public byte[] getBody() throws AuthFailureError {
        return buildMultiPartData(mParams, mBinaryType, mBinaryData);
    }

    @Override
    public String getBodyContentType() {
        return "multipart/form-data; charset=" + this.getParamsEncoding()
                + "; boundary=" + BOUNDARY;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(
            NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));

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

    private byte[] buildMultiPartData(Map<String, Object> params,
            int binaryType, byte[] binaryData) {
        byte[] multipart = null;
        try {
            StringBuffer sb = new StringBuffer();

            if (params != null && params.size() > 0) {
                Iterator uee = params.entrySet().iterator();

                while (uee.hasNext()) {
                    Map.Entry entry = (Map.Entry) uee.next();
                    sb = sb.append("--");
                    sb = sb.append(BOUNDARY);
                    sb = sb.append("\r\n");
                    sb = sb.append(
                            "Content-Disposition: form-data; name=\"" + entry
                                    .getKey() + "\"\r\n\r\n");
                    sb = sb.append(String.valueOf(entry.getValue()));
                    sb = sb.append("\r\n");
                }
            }

            sb = sb.append("--");
            sb = sb.append(BOUNDARY);
            sb = sb.append("\r\n");
            switch (binaryType) {
                case BINARY_TYPE.IMAGE:
                    sb = sb.append(
                            "Content-Disposition: form-data; name=\"file\"; filename=\""
                                    + sdf.format(System.currentTimeMillis())
//                                    + UserInfo.getInstance().getClientId()
                                    + ".jpg\"\r\n");
                    sb = sb.append("Content-Type: image/jpg\r\n\r\n");
                    break;
                case BINARY_TYPE.VOICE:
                    sb = sb.append(
                            "Content-Disposition: form-data; name=\"file\"; filename=\""
                                    + sdf.format(System.currentTimeMillis())
//                                    + UserInfo.getInstance().getClientId()
                                    + ".spx\"\r\n");
                    sb = sb.append(
                            "Content-Type: application/octet-stream\r\n\r\n");
                    break;
            }
            byte[] begin_data = sb.toString().getBytes("UTF-8");
            byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n")
                    .getBytes("UTF-8");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(begin_data);
            baos.write(binaryData);
            baos.write(end_data);
            multipart = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return multipart;
    }

    public interface BINARY_TYPE {
        int IMAGE = 0;
        int VOICE = 1;
    }
}

