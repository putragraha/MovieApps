package com.nsystem.data.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.nsystem.data.entity.TrailerEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

public class TrailerEntityJsonMapper {

    private final Gson gson;

    public TrailerEntityJsonMapper() {
        this.gson = new Gson();
    }

    public TrailerEntity transform(String trailerJsonResponse) throws JsonSyntaxException {
        final Type trailerEntityType = new TypeToken<TrailerEntity>(){}.getType();
        return this.gson.fromJson(trailerJsonResponse, trailerEntityType);
    }

    public List<TrailerEntity> transformCollection(String movieListJsonResponse)
            throws  JsonSyntaxException, JSONException {
        JSONObject movieJsonObject = new JSONObject(movieListJsonResponse);
        String movieListJson = movieJsonObject.getString("results");
        final Type movieEntityTypeList = new TypeToken<List<TrailerEntity>>() {}.getType();
        return this.gson.fromJson(movieListJson, movieEntityTypeList);
    }
}
