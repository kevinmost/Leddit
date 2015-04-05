package com.kevinmost.leddit;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.kevinmost.leddit.module.AppModule;
import dagger.ObjectGraph;

public class App extends Application {
    private static App instance;

    private ObjectGraph objectGraph;

    public static App get() {
        return instance;
    }

    private static void setInstance(App app) {
        if (instance != null && instance != app) {
            throw new RuntimeException("More than one instance of singleton BaseApp has been registered.");
        }
        instance = app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);
        objectGraph = ObjectGraph.create(new AppModule(this));
        objectGraph.inject(this);
    }

    public int getVersionCode() {
        return getPackageInfo().versionCode;
    }

    public String getVersionName() {
        return getPackageInfo().versionName;
    }

    private PackageInfo getPackageInfo() {
        try {
            return getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
