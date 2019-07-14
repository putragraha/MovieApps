package com.nsystem.data.mapper;

import com.nsystem.data.entity.MovieEntity;
import com.nsystem.data.repository.source.network.RestApi;
import com.nsystem.domain.model.Movie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class MovieEntityDataMapper {

    private final TrailerEntityDataMapper trailerEntityDataMapper;

    @Inject
    MovieEntityDataMapper() {
        trailerEntityDataMapper = new TrailerEntityDataMapper();
    }

    public Movie transform(MovieEntity movieEntity) {
        Movie movie = null;
        if (movieEntity != null) {
            movie = new Movie(movieEntity.getMovieId());
            movie.setOriginalTitle(movieEntity.getOriginalTitle());
            movie.setOverview(movieEntity.getOverview());
            movie.setPosterPath(
                    String.format(
                            Locale.ENGLISH,
                            RestApi.API_IMAGE_URL,
                            RestApi.IMAGE_SIZE_200,
                            movieEntity.getPosterPath()
                    )
            );
            movie.setGenres(movieEntity.getGenres());
            movie.setReleaseDate(movieEntity.getReleaseDate());
            movie.setDuration(movieEntity.getDuration());
            movie.setPopularity(movieEntity.getPopularity());
            movie.setTrailers(trailerEntityDataMapper.transform(movieEntity.getTrailers()));
        }
        return movie;
    }

    public List<Movie> transform(Collection<MovieEntity> movieEntityCollection) {
        final List<Movie> movieList = new ArrayList<>();
        for (MovieEntity movieEntity : movieEntityCollection) {
            final Movie movie = transform(movieEntity);
            if (movie != null) {
                movieList.add(movie);
            }
        }
        return movieList;
    }
}
