package com.nsystem.ntheatre.internal.di.components;

import android.app.Activity;

import com.nsystem.ntheatre.internal.di.PerActivity;
import com.nsystem.ntheatre.internal.di.modules.ActivityModule;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();
}
