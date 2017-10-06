package com.example.logonrm.minhasatividades;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.facebook.stetho.Stetho;

/**
 * Created by logonrm on 06/10/2017.
 */

public class App extends Application{

    @Override
    public void onCreate(){
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        ActiveAndroid.initialize(this);
    }
}
