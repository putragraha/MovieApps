package com.nsystem.data.repository.source;

import android.content.Context;

import androidx.annotation.NonNull;

import com.nsystem.data.mapper.MovieEntityJsonMapper;
import com.nsystem.data.repository.source.network.RestApi;
import com.nsystem.data.repository.source.network.RestApiImplementation;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MovieDataFactory {

    private final Context context;

    @Inject
    MovieDataFactory(@NonNull Context context) {
        this.context = context.getApplicationContext();
    }

    public MovieData createCloudData() {
        final MovieEntityJsonMapper movieEntityJsonMapper = new MovieEntityJsonMapper();
        final RestApi restApi = new RestApiImplementation(this.context, movieEntityJsonMapper);

        return new CloudMovieData(restApi);
    }
}