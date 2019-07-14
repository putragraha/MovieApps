package com.nsystem.ntheatre.internal.di.modules;

import android.content.Context;

import com.nsystem.data.executor.JobExecutor;
import com.nsystem.data.repository.MovieDataRepository;
import com.nsystem.domain.executor.PostExecutionThread;
import com.nsystem.domain.executor.ThreadExecutor;
import com.nsystem.domain.repository.MovieRepository;
import com.nsystem.ntheatre.AndroidApplication;
import com.nsystem.ntheatre.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    MovieRepository provideMovieRepository(MovieDataRepository movieDataRepository) {
        return movieDataRepository;
    }
}
