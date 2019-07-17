package com.nsystem.ntheatre.view;

import com.nsystem.ntheatre.model.FavouriteModel;

import java.util.Collection;

public interface FavouriteListView extends LoadDataView {

    void renderFavouriteList(Collection<FavouriteModel> favouriteModelCollection);
    void viewFavourite(FavouriteModel favouriteModel);

}
