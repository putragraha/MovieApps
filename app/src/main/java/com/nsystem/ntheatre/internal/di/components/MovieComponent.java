package com.nsystem.ntheatre.internal.di.components;

import com.nsystem.ntheatre.internal.di.PerActivity;
import com.nsystem.ntheatre.internal.di.modules.ActivityModule;
import com.nsystem.ntheatre.internal.di.modules.MovieModule;
import com.nsystem.ntheatre.view.fragment.FavouriteMovieListFragment;
import com.nsystem.ntheatre.view.fragment.MovieDetailsFragment;
import com.nsystem.ntheatre.view.fragment.PopularMovieListFragment;
import com.nsystem.ntheatre.view.fragment.TopRatedMovieListFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, MovieModule.class})
public interface MovieComponent extends ActivityComponent {
    void inject(PopularMovieListFragment popularMovieListFragment);
    void inject(TopRatedMovieListFragment topRatedMovieListFragment);
    void inject(FavouriteMovieListFragment favouritedMovieListFragment);
    void inject(MovieDetailsFragment movieDetailsFragment);
}
