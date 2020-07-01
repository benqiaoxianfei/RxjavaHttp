package com.example.rxjavahttp;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class DefaultTransformer<T> implements ObservableTransformer<T, T> {
    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).map(new Function<T, T>() {
                    @Override
                    public T apply(T t) throws Exception {
                        return t;
                    }
                });
    }

    public static <T> DefaultTransformer<T> create() {
        return new DefaultTransformer<>();
    }

}
