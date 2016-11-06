package edu.xtu.androidbase.weaher.util.volley;

import android.text.TextUtils;

import com.android.volley.ParseError;
import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.lang.reflect.Modifier;

import edu.xtu.androidbase.weaher.util.AppInfo;
import edu.xtu.androidbase.weaher.util.LogUtils;

/**
 * Created by skyar@live.cn.If you have any questions please contact me!
 */
public class VolleyListenerImpl implements Response.Listener<JSONObject> {

    private final Gson gson = new GsonBuilder()
            .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
            .serializeNulls()
            .create();

    private ApiRequest mApiRequest;

    public VolleyListenerImpl(ApiRequest apiRequest) {
        this.mApiRequest = apiRequest;
    }

    @Override
    public void onResponse(JSONObject response) {
        LogUtils.d("volley_response", "success " + response.toString());
        try {
            boolean success = false;
            if (response.has("success")) {
                success = response.getBoolean("success");
            }
            int code = -1;
            if (response.has("code")) {
                code = response.getInt("code");
            }
            String message = "";
            if (response.has("msg")) {
                message = response.getString("msg");
            }

            if (success || code == 0) {
                JSONObject data;
                if (TextUtils.isEmpty(mApiRequest.getGsonKey())) {
                    data = response;
                } else {
                    data = response.getJSONObject(mApiRequest.getGsonKey());
                }

                if (mApiRequest != null
                        && mApiRequest.getApiRequestListener() != null) {
                    if (mApiRequest.getDataClass() != null) {
                        mApiRequest.getApiRequestListener().onSuccessResponse(
                                gson.fromJson(data.toString(),
                                        mApiRequest.getDataClass()));
                    } else {
                        mApiRequest.getApiRequestListener()
                                .onSuccessResponse(data.toString());
                    }
                }
            } else {
                if (mApiRequest != null
                        && mApiRequest.getApiRequestListener() != null) {
                    mApiRequest.getApiRequestListener()
                            .onErrorResponse(new ApiError(code, message));
                }

//                 登录失效，重新登录
                if (code == 300) {
//                    ToastUtils.getInstance().showToast("请重新登录试试！");

//                        UserInfo.getInstance()
//                                .logout(ActivityStack.getInstance()
//                                        .getTopActivity());
//                    TerminalActivity.showFragment(ActivityStack.getInstance()
//                            .getTopActivity(), LoginFragment.class,null);

                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
            if (mApiRequest != null
                    && mApiRequest.getApiRequestListener() != null) {
                if (AppInfo.getAppInstant().isDebug()) {
                    AppInfo.getAppInstant().getUiHandler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
//                            ToastUtils.getInstance().showToast(false, e.getMessage());
                        }
                    }, 100);
                }
                mApiRequest.getApiRequestListener()
                        .onErrorResponse(new ParseError(e));
            }
        }
    }
}
