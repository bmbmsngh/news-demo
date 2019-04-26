package com.bambamsingh.newsdemo.base;

import com.bambamsingh.newsdemo.utilites.Utils;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;
import okhttp3.OkHttpClient;

public class App extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);

        if (Utils.isDebug()) {
            Stetho.initializeWithDefaults(this);
            new OkHttpClient.Builder()
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();
        }
    }
}
