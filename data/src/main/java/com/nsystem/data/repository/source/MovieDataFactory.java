package com.nsystem.data.repository.source;

import android.content.Context;

import androidx.annotation.NonNull;

import com.nsystem.data.mapper.MovieEntityJsonMapper;
import com.nsystem.data.mapper.TrailerEntityJsonMapper;
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
        final TrailerEntityJsonMapper trailerEntityJsonMapper = new TrailerEntityJsonMapper();
        final RestApi restApi = new RestApiImplementation(this.context, movieEntityJsonMapper, trailerEntityJsonMapper);

        return new CloudMovieData(restApi);
    }

    public MovieData createLocalData() {
        return new LocalMovieData(context);
    }
}