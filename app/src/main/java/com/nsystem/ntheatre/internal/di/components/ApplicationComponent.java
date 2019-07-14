package com.nsystem.ntheatre.internal.di.components;

import android.content.Context;

import com.nsystem.domain.executor.PostExecutionThread;
import com.nsystem.domain.executor.ThreadExecutor;
import com.nsystem.domain.repository.MovieRepository;
import com.nsystem.ntheatre.view.activity.BaseActivity;
import com.nsystem.ntheatre.internal.di.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    MovieRepository movieRepository();
}
