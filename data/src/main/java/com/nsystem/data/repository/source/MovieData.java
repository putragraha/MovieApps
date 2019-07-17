package com.nsystem.data.repository.source;

import com.nsystem.data.entity.FavouriteEntity;
import com.nsystem.data.entity.MovieEntity;
import com.nsystem.domain.model.Favourite;

import java.util.List;

import io.reactivex.Observable;

public interface MovieData {

    Observable<List<MovieEntity>> popularMovieEntityList();
    Observable<List<MovieEntity>> topRatedMovieEntityList();
    Observable<List<FavouriteEntity>> favouriteMovieEntityList();
    Observable<MovieEntity> movieEntity(final int movieId);
    Observable<Long> addFavouriteEntity(FavouriteEntity favouriteEntity);
    Observable<Integer> unFavouriteEntity(FavouriteEntity favouriteEntity);
}