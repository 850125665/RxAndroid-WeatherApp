package edu.xtu.androidbase.weaher.util.Retrofit;

/**
 * Created by huilin on 2016/11/6.
 */
public interface HttpClientListner<T> {
    void success(T t);
    void error(Throwable error);
}
