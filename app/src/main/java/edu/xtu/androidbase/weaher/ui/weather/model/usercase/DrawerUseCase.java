package edu.xtu.androidbase.weaher.ui.weather.model.usercase;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import edu.xtu.androidbase.weaher.util.RxUtil.UseCase;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

/**
 * Created by huilin on 2016/12/5.
 */

public class DrawerUseCase extends UseCase<Void, DrawerLayout> {
    private static final float OFFSET_THRESHOLD = 0.03f;
    @Override
    public Observable<Void> createObservable(final DrawerLayout drawerLayout) {
        Observable<Void> observable = Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(final Subscriber<? super Void> subscriber) {
                drawerLayout.closeDrawer(GravityCompat.START);
                final DrawerLayout.DrawerListener drawerListener = new DrawerLayout.SimpleDrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        if (slideOffset < OFFSET_THRESHOLD) {
                            subscriber.onNext(null);
                            subscriber.onCompleted();
                        }
                    }
                };
                drawerLayout.addDrawerListener(drawerListener);
                subscriber.add(new MainThreadSubscription() {
                    @Override
                    protected void onUnsubscribe() {
                        drawerLayout.removeDrawerListener(drawerListener);
                    }
                });
            }
        });
        return observable;
    }
}
