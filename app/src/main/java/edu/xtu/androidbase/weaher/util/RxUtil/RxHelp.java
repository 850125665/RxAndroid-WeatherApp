package edu.xtu.androidbase.weaher.util.RxUtil;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by huilin on 2016/11/6.
 */
public class RxHelp {

    public static <T>Observable.Transformer<T,T> onlineSchedul(){
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return  tObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
    private static final Observable.Transformer executorTransformer = new Observable.Transformer() {
        @Override public Object call(Object observable) {
            return ((Observable) observable).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };
    public static <T> Observable.Transformer<T, T> applyExecutorSchedulers() {
        return (Observable.Transformer<T, T>) executorTransformer;
    }
    public static <T>Observable.Transformer<T,T> mainSchedul(){
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());

            }
        };
    }


}
