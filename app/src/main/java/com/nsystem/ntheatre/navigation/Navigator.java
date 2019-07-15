package com.nsystem.ntheatre.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.nsystem.ntheatre.view.activity.MovieDetailsActivity;
import com.nsystem.ntheatre.view.activity.PopularMovieListActivity;
import com.nsystem.ntheatre.view.activity.TopRatedMovieListActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Navigator {

    @Inject
    public Navigator(){}

    public void navigateToPopularMovieList(Context context) {
        if (context != null) {
            Intent intentToLaunch = PopularMovieListActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToTopRatedMovieList(Context context) {
        if (context != null) {
            Intent intentToLaunch = TopRatedMovieListActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public void navigateToMovieDetails(Context context, int movieId) {
        if (context != null) {
            Intent intentToLaunch = MovieDetailsActivity.getCallingIntent(context, movieId);
            context.startActivity(intentToLaunch);
        }
    }
}
