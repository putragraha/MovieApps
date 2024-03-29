package com.nsystem.data.repository.source;

import com.nsystem.data.entity.FavouriteEntity;
import com.nsystem.data.entity.MovieEntity;
import com.nsystem.data.repository.source.network.RestApi;

import java.util.List;

import io.reactivex.Observable;

public class CloudMovieData implements MovieData {

    private final RestApi restApi;

    public CloudMovieData(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<List<MovieEntity>> popularMovieEntityList() {
        return this.restApi.popularMovieEntityList();
    }

    @Override
    public Observable<List<MovieEntity>> topRatedMovieEntityList() {
        return this.restApi.topRatedMovieEntityList();
    }

    @Override
    public Observable<List<FavouriteEntity>> favouriteMovieEntityList() {
        return null;
    }

    @Override
    public Observable<MovieEntity> movieEntity(int movieId) {
        return this.restApi.movieEntity(movieId);
    }

    @Override
    public Observable<Long> addFavouriteEntity(FavouriteEntity favouriteEntity) {
        return null;
    }

    @Override
    public Observable<Integer> unFavouriteEntity(FavouriteEntity favouriteEntity) {
        return null;
    }
}
