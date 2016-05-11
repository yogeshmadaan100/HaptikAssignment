package com.haptik.haptikassignment.generators;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.haptik.haptikassignment.R;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.client.Client;
import retrofit.client.OkClient;

/**
 * Created by yogeshmadaan on 11/05/16.
 */
public class ServiceGenerator {
    private static final String BASE_URL = "http://haptik.co/android/";
    private static RestAdapter.LogLevel logLevel = RestAdapter.LogLevel.FULL;

    public static <S> S createService(Class<S> serviceClass, final Context context) {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .setLogLevel(logLevel)
                .setLog(new AndroidLog("Haptik"))
                //.setErrorHandler(new AppErrorHandler(context))  // use error handler..
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        try {
                            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                            request.addHeader("versionCode",String.valueOf(pInfo.versionCode));
                            request.addHeader("versionName",String.valueOf(pInfo.versionName));
                            request.addHeader("app_type","android");
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .setClient(createOkHttpClient(context));
        RestAdapter adapter = builder.build();
        return adapter.create(serviceClass);
    }
    private static Client createOkHttpClient(Context context) {
        OkHttpClient client = new OkHttpClient();
        int connTimeout = context.getResources().getInteger(R.integer.http_client_connection_timeout);
        int readTimeout = context.getResources().getInteger(R.integer.http_client_read_timeout);
        client.setConnectTimeout(connTimeout, TimeUnit.SECONDS);
        client.setReadTimeout(readTimeout, TimeUnit.SECONDS);
        return new OkClient(client);
    }
}
