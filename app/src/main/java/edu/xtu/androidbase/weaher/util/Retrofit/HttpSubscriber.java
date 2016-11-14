package edu.xtu.androidbase.weaher.util.Retrofit;

import android.app.Dialog;

import edu.xtu.androidbase.weaher.util.AppInfo;
import edu.xtu.androidbase.weaher.util.AppMethods;
import rx.Subscriber;

/**
 * Created by huilin on 2016/11/14.
 */
public abstract class HttpSubscriber<T extends HttpModel> extends Subscriber<T> {

    private Dialog dialog;

    public HttpSubscriber() {
        dialog = AppMethods.createLoadingDialog(AppInfo.getAppInstant().getMyContext());
    }

    @Override
    public void onCompleted() {
//        AppMethods.dismissDialog();
    }

    @Override
    public void onError(Throwable e) {
        HttpModel httpModel = new HttpModel();
        httpModel.setCode(1000);
        httpModel.setMsg("网络异常");
        error(httpModel);
    }

    @Override
    public void onNext(T t) {
        if(t.getCode() == 200){
            //成功
        }else{
            //服务器异常
        }
        success(t);
    }

    @Override
    public void onStart() {

    }


    /**
     * 错误信息
     */
    public abstract void error(HttpModel httpModel);

    /**
     * 成功信息
     * @param t
     */
    public abstract void success(T t);
}
