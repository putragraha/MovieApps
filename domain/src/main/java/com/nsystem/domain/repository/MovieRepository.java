package com.nsystem.domain.repository;

import com.nsystem.domain.model.Favourite;
import com.nsystem.domain.model.Movie;

import java.util.List;

import io.reactivex.Observable;

public interface MovieRepository {
    Observable<List<Movie>> popularMovies();
    Observable<List<Movie>> topRatedMovies();
    Observable<List<Favourite>> favouriteMovies();
    Observable<Movie> movie(final int movieId);
    Observable<Long> addFavourite(Favourite favourite);
}
