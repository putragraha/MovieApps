package com.nsystem.domain.model;

public class Favourite {

    private int movieId;
    private String posterPath;

    public int getMovieId() {
        return movieId;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
