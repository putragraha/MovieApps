package com.nsystem.ntheatre.model;


import com.nsystem.domain.model.Genre;

import java.util.List;

public class MovieModel {

    private final int movieId;

    private String originalTitle;
    private String overview;
    private String posterPath;
    private List<Genre> genres;
    private String releaseDate;
    private int duration;
    private String popularity;
    private List<TrailerModel> trailerModelList;

    public MovieModel(int movieId) {
        this.movieId = movieId;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public List<TrailerModel> getTrailerModelList() {
        return trailerModelList;
    }

    public void setTrailerModelList(List<TrailerModel> trailerModelList) {
        this.trailerModelList = trailerModelList;
    }
}

