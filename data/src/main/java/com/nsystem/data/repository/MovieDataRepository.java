package com.nsystem.data.repository;

import com.nsystem.data.mapper.FavouriteMovieEntityDataMapper;
import com.nsystem.data.mapper.MovieEntityDataMapper;
import com.nsystem.data.repository.source.MovieData;
import com.nsystem.data.repository.source.MovieDataFactory;
import com.nsystem.domain.model.Favourite;
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
    private final FavouriteMovieEntityDataMapper favouriteMovieEntityDataMapper;

    @Inject
    public MovieDataRepository(MovieDataFactory movieDataFactory,
                               MovieEntityDataMapper movieEntityDataMapper,
                               FavouriteMovieEntityDataMapper favouriteMovieEntityDataMapper) {
        this.movieDataFactory = movieDataFactory;
        this.movieEntityDataMapper = movieEntityDataMapper;
        this.favouriteMovieEntityDataMapper = favouriteMovieEntityDataMapper;
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
    public Observable<List<Favourite>> favouriteMovies() {
        final MovieData movieData = this.movieDataFactory.createLocalData();
        return movieData.favouriteMovieEntityList().map(this.favouriteMovieEntityDataMapper::transform);
    }

    @Override
    public Observable<Movie> movie(int movieId) {
        final MovieData movieData = this.movieDataFactory.createCloudData();
        return movieData.movieEntity(movieId).map(this.movieEntityDataMapper::transform);
    }

    @Override
    public Observable<Long> addFavourite(Favourite favourite) {
        final MovieData movieData = this.movieDataFactory.createLocalData();
        return movieData.addFavouriteEntity(this.favouriteMovieEntityDataMapper.deform(favourite));
    }

    @Override
    public Observable<Integer> unFavourite(Favourite favourite) {
        final MovieData movieData = this.movieDataFactory.createLocalData();
        return movieData.unFavouriteEntity(this.favouriteMovieEntityDataMapper.deform(favourite));
    }
}
