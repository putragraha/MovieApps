package com.nsystem.data.mapper;

import com.nsystem.data.entity.FavouriteEntity;
import com.nsystem.data.entity.MovieEntity;
import com.nsystem.data.entity.TrailerEntity;
import com.nsystem.data.repository.source.network.RestApi;
import com.nsystem.domain.model.Favourite;
import com.nsystem.domain.model.Movie;
import com.nsystem.domain.model.Trailer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class FavouriteMovieEntityDataMapper {

    @Inject
    FavouriteMovieEntityDataMapper() {}

    public Favourite transform(FavouriteEntity favouriteEntity) {
        Favourite favourite = null;
        if (favouriteEntity != null) {
            favourite = new Favourite();
            favourite.setMovieId(favouriteEntity.getMovieId());
            favourite.setPosterPath(favouriteEntity.getPosterPath());
        }
        return favourite;
    }

    public List<Favourite> transform(Collection<FavouriteEntity> favouriteEntityCollection) {
        final List<Favourite> favouriteList = new ArrayList<>();
        for (FavouriteEntity favouriteEntity : favouriteEntityCollection) {
            final Favourite favourite = transform(favouriteEntity);
            if (favourite != null) {
                favouriteList.add(favourite);
            }
        }
        return favouriteList;
    }

    public FavouriteEntity deform(Favourite favourite) {
        FavouriteEntity favouriteEntity = null;
        if (favourite != null) {
            favouriteEntity = new FavouriteEntity();
            favouriteEntity.setMovieId(favourite.getMovieId());
            favouriteEntity.setPosterPath(favourite.getPosterPath());
        }
        return favouriteEntity;
    }

    public List<FavouriteEntity> deform(Collection<Favourite> favouriteCollection) {
        final List<FavouriteEntity> favouriteEntityList = new ArrayList<>();
        for (Favourite favourite : favouriteCollection) {
            final FavouriteEntity favouriteEntity = deform(favourite);
            if (favouriteEntity != null) {
                favouriteEntityList.add(favouriteEntity);
            }
        }
        return favouriteEntityList;
    }
}
