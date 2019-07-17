package com.nsystem.ntheatre.mapper;

import com.nsystem.domain.model.Favourite;
import com.nsystem.ntheatre.model.FavouriteModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

public class FavouriteModelDataMapper {

    @Inject
    public FavouriteModelDataMapper() {}

    public FavouriteModel transform(Favourite favourite) {
        if (favourite == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }

        return new FavouriteModel(
                favourite.getMovieId(),
                favourite.getPosterPath());
    }

    public Collection<FavouriteModel> transform(Collection<Favourite> favouriteCollection) {
        Collection<FavouriteModel> favouriteModelCollection;

        if (favouriteCollection != null && !favouriteCollection.isEmpty()) {
            favouriteModelCollection = new ArrayList<>();
            for (Favourite favourite : favouriteCollection) {
                favouriteModelCollection.add(transform(favourite));
            }
        } else {
            favouriteModelCollection = Collections.emptyList();
        }

        return favouriteModelCollection;
    }

}
