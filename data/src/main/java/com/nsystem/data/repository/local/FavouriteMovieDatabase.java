package com.nsystem.data.repository.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nsystem.data.entity.FavouriteEntity;

@Database(entities = {FavouriteEntity.class}, version = 1, exportSchema = false)
public abstract class FavouriteMovieDatabase extends RoomDatabase {

    private static FavouriteMovieDatabase instance;

    public abstract FavouriteMovieDao getMovieDao();

    public static FavouriteMovieDatabase getDBInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    FavouriteMovieDatabase.class,
                    "nTheatre")
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }

}
