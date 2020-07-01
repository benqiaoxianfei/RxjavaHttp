package com.example.rxjavahttp;

import android.content.Context;
import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class SubcriberDialog<T> implements Observer<T> {

    private static final String TAG =SubcriberDialog.class.getCanonicalName();

    private Disposable d;
    private boolean isShowDialog;
    private ProgersssDialog progressDialog;
    private Context mContext;

    public SubcriberDialog(Context context,boolean isShowDialog) {
        this.isShowDialog = isShowDialog;
        this.mContext = context;
    }

    @Override
    public void onSubscribe(final Disposable d) {
        this.d = d;
        if (progressDialog == null && isShowDialog) {
            progressDialog = new ProgersssDialog(mContext);
            progressDialog.show();
        }
        Log.e(TAG, "onSubscribe");
    }

    @Override
    public void onComplete() {
        if (!d.isDisposed()) {
            d.dispose();
        }
        if (progressDialog != null && isShowDialog) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        Log.e(TAG, "onComplete");
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        String errorMsg = HttpErrorMsg.error(mContext,e);
        onFailure(errorMsg);
        if (progressDialog != null && isShowDialog) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public abstract void onSuccess(T t);

    public abstract void onFailure(String errorMsg);

}
