package edu.xtu.androidbase.weaher.util.RxUtil;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by huilin on 2016/11/6.
 */
public abstract class UseCase<T,R> {

    private Subscription subscription;
    public abstract Observable<T> createObservable(R r);

    public void subscriber(Observer<T> observer, R r){
        subscription = createObservable(r)
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
