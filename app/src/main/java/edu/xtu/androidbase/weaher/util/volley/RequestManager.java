package edu.xtu.androidbase.weaher.util.volley;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import edu.xtu.androidbase.weaher.util.AppInfo;

/**
 * Created by skyar@live.cn.If you have any questions please contact me!
 */
public class RequestManager {
    public static RequestQueue mRequestQueue = Volley
            .newRequestQueue(AppInfo.getAppInstant().getMyContext());

    private RequestManager() {
        // no instances
    }

    public static void addRequest(Request<?> request, Object tag) {
        if (tag != null) {
            request.setTag(tag);
        }
        mRequestQueue.add(request);
    }

    public static void cancelAll(Object tag) {
        mRequestQueue.cancelAll(tag);
    }
}

