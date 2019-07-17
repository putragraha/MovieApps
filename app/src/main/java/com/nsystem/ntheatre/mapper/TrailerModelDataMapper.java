package com.nsystem.ntheatre.mapper;

import com.nsystem.domain.model.Movie;
import com.nsystem.domain.model.Trailer;
import com.nsystem.ntheatre.model.TrailerModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class TrailerModelDataMapper {

    @Inject
    public TrailerModelDataMapper(){}

    public TrailerModel transform(Trailer trailer) {
        if (trailer == null) {
            throw new IllegalArgumentException("Cannot transform a null trailer");
        }

        final TrailerModel trailerModel = new TrailerModel();
        trailerModel.setTrailerid(trailer.getTrailerId());
        trailerModel.setKey(trailer.getKey());
        trailerModel.setName(trailer.getName());

        return trailerModel;
    }

    public List<TrailerModel> transform(Collection<Trailer> trailerCollection) {
        List<TrailerModel> trailerModelCollection;

        if (trailerCollection != null && !trailerCollection.isEmpty()) {
            trailerModelCollection = new ArrayList<>();
            for (Trailer trailer : trailerCollection) {
                trailerModelCollection.add(transform(trailer));
            }
        } else {
            trailerModelCollection = Collections.emptyList();
        }

        return trailerModelCollection;
    }
}
