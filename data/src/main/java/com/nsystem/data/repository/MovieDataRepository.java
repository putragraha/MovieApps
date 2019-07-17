package com.nsystem.data.repository;

import com.nsystem.data.mapper.MovieEntityDataMapper;
import com.nsystem.data.repository.source.MovieData;
import com.nsystem.data.repository.source.MovieDataFactory;
import com.nsystem.domain.model.Movie;
import com.nsystem.domain.repository.MovieRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class MovieDataRepository implements MovieRepository {

    private final MovieDataFactory movieDataFactory;
    private final MovieEntityDataMapper movieEntityDataMapper;

    @Inject
    public MovieDataRepository(MovieDataFactory movieDataFactory,
                               MovieEntityDataMapper movieEntityDataMapper) {
        this.movieDataFactory = movieDataFactory;
        this.movieEntityDataMapper = movieEntityDataMapper;
    }

    @Override
    public Observable<List<Movie>> popularMovies() {
        final MovieData movieData = this.movieDataFactory.createCloudData();
        return movieData.popularMovieEntityList().map(this.movieEntityDataMapper::transform);
    }

    @Override
    public Observable<List<Movie>> topRatedMovies() {
        final MovieData movieData = this.movieDataFactory.createCloudData();
        return movieData.topRatedMovieEntityList().map(this.movieEntityDataMapper::transform);
    }

    @Override
    public Observable<Movie> movie(int movieId) {
        final MovieData movieData = this.movieDataFactory.createCloudData();
        return movieData.movieEntity(movieId).map(this.movieEntityDataMapper::transform);
    }
}
