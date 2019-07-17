package com.nsystem.data.repository.local;

import androidx.room.Dao;
import androidx.room.Query;

import com.nsystem.data.entity.FavouriteEntity;

import java.util.List;

import io.reactivex.Observable;

@Dao
public interface FavouriteMovieDao {

    @Query("SELECT * FROM Favourite")
    Observable<List<FavouriteEntity>> getFavouriteMovieList();
}
