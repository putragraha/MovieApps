package com.nsystem.data.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.nsystem.data.entity.MovieEntity;
import com.nsystem.data.entity.TrailerEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

public class TrailerEntityJsonMapper {
    private final Gson gson;

    @Inject
    public TrailerEntityJsonMapper(){
        this.gson = new Gson();
    }

    public List<TrailerEntity> transformTrailerEntityCollection(String trailerListJsonResponse)
            throws  JsonSyntaxException, JSONException {
        JSONObject trailerJsonObject = new JSONObject(trailerListJsonResponse);
        String trailerListJson = trailerJsonObject.getString("results");
        final Type trailerEntityTypeList = new TypeToken<List<TrailerEntity>>() {}.getType();
        return this.gson.fromJson(trailerListJson, trailerEntityTypeList);
    }
}
