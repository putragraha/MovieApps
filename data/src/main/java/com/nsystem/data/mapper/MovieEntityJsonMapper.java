package com.nsystem.data.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.nsystem.data.entity.MovieEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

public class MovieEntityJsonMapper {

    private final Gson gson;

    @Inject
    public MovieEntityJsonMapper(){
        this.gson = new Gson();
    }

    public MovieEntity transformMovieEntity(String movieJsonResponse) throws JsonSyntaxException {
        final Type movieEntityType = new TypeToken<MovieEntity>() {}.getType();
        return this.gson.fromJson(movieJsonResponse, movieEntityType);
    }

    public List<MovieEntity> transformMovieEntityCollection(String movieListJsonResponse)
            throws  JsonSyntaxException, JSONException {
        JSONObject movieJsonObject = new JSONObject(movieListJsonResponse);
        String movieListJson = movieJsonObject.getString("results");
        final Type movieEntityTypeList = new TypeToken<List<MovieEntity>>() {}.getType();
        return this.gson.fromJson(movieListJson, movieEntityTypeList);
    }
}
