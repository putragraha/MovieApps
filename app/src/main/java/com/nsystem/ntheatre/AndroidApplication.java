package com.nsystem.ntheatre;

import android.app.Application;

import com.nsystem.ntheatre.internal.di.components.ApplicationComponent;
import com.nsystem.ntheatre.internal.di.components.DaggerApplicationComponent;
import com.nsystem.ntheatre.internal.di.modules.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;

public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
        this.initializeLeakDetecttion();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    private void initializeLeakDetecttion() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }
}
