package com.nsystem.data.repository.source.network;

import com.nsystem.data.entity.MovieEntity;

import java.util.List;

import io.reactivex.Observable;

public interface RestApi {
    String API_BASE_URL = "https://api.themoviedb.org/3/movie/";
    String API_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/";

    String API_KEY = "299aa91229ad482944c2c6462a0d9117";

    String API_POPULAR_URL = API_BASE_URL + "popular?" +
            "api_key=" + API_KEY + "&" +
            "language=%s&page=%d";
    String API_TOP_RATED_URL = API_BASE_URL + "top_rated?" +
            "api_key=" + API_KEY + "&" +
            "language=%s&page=%d";
    String API_MOVIE_DETAILS_URL = API_BASE_URL + "%d?" +
            "api_key=" + API_KEY + "&" +
            "language=%s&page=%d";

    String IMAGE_SIZE_200 = "w200";
    String IMAGE_SIZE_500 = "w500";

    String API_IMAGE_URL = API_IMAGE_BASE_URL + "%s%s";

    Observable<List<MovieEntity>> popularMovieEntityList();
    Observable<List<MovieEntity>> topRatedMovieEntityList();
    Observable<MovieEntity> movieEntity(final int movieId);
}
