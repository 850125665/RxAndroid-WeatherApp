package edu.xtu.androidbase.weaher.util.RxUtil;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;

/**
 * Created by huilin on 2016/11/6.
 */
public abstract class UseCase<T,R> {

    public Subscription subscription;
    public abstract Observable<T> createObservable(R r);

    public void subscriber(Observer<T> observer, R r){
        subscription = createObservable(r)
                .onBackpressureBuffer()
                .filter(new Func1<T, Boolean>() {
                    @Override
                    public Boolean call(T t) {
                        return !subscription.isUnsubscribed();
                    }
                })
                .subscribe(observer);
    }

    public void unsubscribe(){
        if(subscription!=null){
            if(subscription.isUnsubscribed()){
                subscription.unsubscribe();
            }
        }
    }

}
