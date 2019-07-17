package com.nsystem.data.repository.source;

import android.content.Context;

import com.nsystem.data.entity.FavouriteEntity;
import com.nsystem.data.entity.MovieEntity;
import com.nsystem.data.repository.local.FavouriteMovieDatabase;
import com.nsystem.data.repository.source.network.RestApi;

import java.util.List;

import io.reactivex.Observable;

public class LocalMovieData implements MovieData {

    private Context context;

    public LocalMovieData(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public Observable<List<MovieEntity>> popularMovieEntityList() {
        return null;
    }

    @Override
    public Observable<List<MovieEntity>> topRatedMovieEntityList() {
        return null;
    }

    @Override
    public Observable<List<FavouriteEntity>> favouriteMovieEntityList() {
        return FavouriteMovieDatabase.getDBInstance(this.context)
                .getMovieDao().getFavouriteMovieList();
    }

    @Override
    public Observable<MovieEntity> movieEntity(int movieId) {
        return null;
    }

    @Override
    public Observable<Long> addFavouriteEntity(FavouriteEntity favouriteEntity) {
        return Observable.just(FavouriteMovieDatabase.getDBInstance(this.context)
                .getMovieDao().addFavouriteMovie(favouriteEntity));
    }

    @Override
    public Observable<Integer> unFavouriteEntity(FavouriteEntity favouriteEntity) {
        return Observable.just(FavouriteMovieDatabase.getDBInstance(this.context)
                .getMovieDao().unFavouriteMovie(favouriteEntity));
    }


}
