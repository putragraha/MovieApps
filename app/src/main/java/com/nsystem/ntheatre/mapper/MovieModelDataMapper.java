package com.nsystem.ntheatre.mapper;


import com.nsystem.domain.model.Movie;
import com.nsystem.ntheatre.model.MovieModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

public class MovieModelDataMapper {

    @Inject
    public MovieModelDataMapper() {}

    public MovieModel transform(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }

        final MovieModel movieModel = new MovieModel(movie.getMovieId());
        movieModel.setOriginalTitle(movie.getOriginalTitle());
        movieModel.setOverview(movie.getOverview());
        movieModel.setGenres(movie.getGenres());
        movieModel.setPosterPath(movie.getPosterPath());
        movieModel.setReleaseDate(movie.getReleaseDate());
        movieModel.setPopularity(movie.getPopularity());
        movieModel.setDuration(movie.getDuration());

        return movieModel;
    }

    public Collection<MovieModel> transform(Collection<Movie> movieCollection) {
        Collection<MovieModel> movieModelCollection;

        if (movieCollection != null && !movieCollection.isEmpty()) {
            movieModelCollection = new ArrayList<>();
            for (Movie movie : movieCollection) {
                movieModelCollection.add(transform(movie));
            }
        } else {
            movieModelCollection = Collections.emptyList();
        }

        return movieModelCollection;
    }
}
