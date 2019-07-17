package com.nsystem.data.mapper;

import com.nsystem.data.entity.TrailerEntity;
import com.nsystem.domain.model.Trailer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class TrailerEntityDataMapper {

    @Inject
    TrailerEntityDataMapper(){}

    public Trailer transform(TrailerEntity trailerEntity) {
        Trailer trailer = null;
        if (trailerEntity != null) {
            trailer = new Trailer();
            trailer.setTrailerId(trailerEntity.getTrailerId());
            trailer.setKey(trailerEntity.getKey());
            trailer.setName(trailerEntity.getName());
        }
        return trailer;
    }

    public List<Trailer> transform(Collection<TrailerEntity> trailerEntityCollection) {
        final List<Trailer> trailerList = new ArrayList<>();
        for (TrailerEntity trailerEntity : trailerEntityCollection) {
            final Trailer trailer = transform(trailerEntity);
            if (trailer != null) {
                trailerList.add(trailer);
            }
        }
        return trailerList;
    }
}
