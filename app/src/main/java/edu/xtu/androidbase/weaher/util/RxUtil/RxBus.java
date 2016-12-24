package edu.xtu.androidbase.weaher.util.RxUtil;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;

/**
 * Created by huilin on 2016/11/6.
 */
public class RxBus {
    //主要通过subject的PublishSubject特性，接受订阅后的消息
    private SerializedSubject<Object,Object> bus;
    private RxBus(){
        bus = new SerializedSubject<>(PublishSubject.create());
    }
    private static class RxBusHolder{
        public static RxBus INSTANCE = new RxBus();
    }
    public static RxBus getInstance(){
        return RxBusHolder.INSTANCE;
    }

    /**
     * 根据传入的evenType来返回特定的被观察者,onBackpressureBuffer防止多任务一起发射信息
     * @param evenType
     * @param <T>
     * @return
     */
    public <T>Observable<T> toObservable(Class<T> evenType){
       return bus.asObservable().ofType(evenType).onBackpressureBuffer();
    }

    public void postEven(Object object){
        bus.onNext(object);
    }

}
