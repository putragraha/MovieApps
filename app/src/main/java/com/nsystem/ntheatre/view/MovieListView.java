package com.nsystem.ntheatre.view;

import com.nsystem.ntheatre.model.MovieModel;

import java.util.Collection;

public interface MovieListView extends LoadDataView {

    void renderMovieList(Collection<MovieModel> movieModelCollection);
    void viewMovie(MovieModel movieModel);

}
