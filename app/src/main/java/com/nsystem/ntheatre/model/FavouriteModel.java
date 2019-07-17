package com.nsystem.ntheatre.model;

public class FavouriteModel {
    private final int movieId;
    private final String posterPath;

    public FavouriteModel(int movieId, String posterPath) {
        this.movieId = movieId;
        this.posterPath = posterPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public int getMovieId() {
        return movieId;
    }
}
