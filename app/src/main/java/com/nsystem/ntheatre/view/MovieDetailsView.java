package com.nsystem.ntheatre.view;

import com.nsystem.ntheatre.model.MovieModel;

public interface MovieDetailsView extends LoadDataView {
    void renderMovie(MovieModel movieModel);
}
