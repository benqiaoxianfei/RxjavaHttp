package com.example.rxjavahttp;

import android.content.Context;
import android.net.ParseException;
import org.json.JSONException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import retrofit2.adapter.rxjava2.HttpException;


public class HttpErrorMsg {
    public static String error(Context context,Throwable e) {
        String errorMsg = context.getResources().getString(R.string.unknown_error);
        if (e instanceof UnknownHostException) {
            errorMsg = context.getResources().getString(R.string.network_is_not);
        } else if (e instanceof SocketTimeoutException) {
            errorMsg = context.getResources().getString(R.string.network_timeout);
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            errorMsg = convertStatusCode(context,httpException);
        } else if (e instanceof ParseException || e instanceof JSONException) {
            errorMsg = context.getResources().getString(R.string.data_parsing_error);
        } else if (e instanceof ConnectException) {
            errorMsg = context.getResources().getString(R.string.not_connect_service);
        }
        return errorMsg;
    }


    private static String convertStatusCode(Context context,HttpException httpException) {

        String msg;
        if (httpException.code() >= 500 && httpException.code() < 600) {
            msg = context.getResources().getString(R.string.service_processing_req_error);
        } else if (httpException.code() >= 400 && httpException.code() < 500) {
            msg = context.getResources().getString(R.string.service_unable_to_req);
        } else if (httpException.code() >= 300 && httpException.code() < 400) {
            msg = context.getResources().getString(R.string.req_redirect_other_pages);
        } else {
            msg = httpException.message();
        }
        return msg;
    }

}
