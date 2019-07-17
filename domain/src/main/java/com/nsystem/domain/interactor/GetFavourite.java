package com.nsystem.domain.interactor;

import com.nsystem.domain.UseCase;
import com.nsystem.domain.executor.PostExecutionThread;
import com.nsystem.domain.executor.ThreadExecutor;
import com.nsystem.domain.model.Favourite;
import com.nsystem.domain.repository.MovieRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetFavourite extends UseCase<List<Favourite>, Void> {

    private final MovieRepository movieRepository;

    @Inject
    public GetFavourite(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, MovieRepository movieRepository) {
        super(threadExecutor, postExecutionThread);
        this.movieRepository = movieRepository;
    }

    @Override
    public Observable<List<Favourite>> buildUseCaseObservable(Void aVoid) {
        return this.movieRepository.favouriteMovies();
    }
}
