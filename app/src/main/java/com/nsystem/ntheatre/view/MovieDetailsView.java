package com.nsystem.ntheatre.view;

import com.nsystem.ntheatre.model.MovieModel;
import com.nsystem.ntheatre.model.TrailerModel;

public interface MovieDetailsView extends LoadDataView {
    void renderMovie(MovieModel movieModel);
    void openTrailer(TrailerModel trailerModel);
}
