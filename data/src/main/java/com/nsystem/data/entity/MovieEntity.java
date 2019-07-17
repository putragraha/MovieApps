package com.nsystem.data.entity;

import com.google.gson.annotations.SerializedName;
import com.nsystem.domain.model.Genre;

import java.util.List;

public class MovieEntity {

    @SerializedName("id")
    private int movieId;

    @SerializedName("original_title")
    private String originalTitle;

    private String overview;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("genres")
    private List<Genre> genres;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("runtime")
    private int duration;

    @SerializedName("vote_average")
    private String popularity;

    private List<TrailerEntity> trailerEntityList;

    public int getMovieId() {
        return movieId;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getDuration() {
        return duration;
    }

    public String getPopularity() {
        return popularity;
    }

    public List<TrailerEntity> getTrailerEntityList() {
        return trailerEntityList;
    }

    public void setTrailerEntityList(List<TrailerEntity> trailerEntityList) {
        this.trailerEntityList = trailerEntityList;
    }
}
