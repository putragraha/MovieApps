package com.nsystem.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Favourite")
public class FavouriteEntity {

    @PrimaryKey
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
