package com.nsystem.domain.interactor;

import com.nsystem.domain.UseCase;
import com.nsystem.domain.executor.PostExecutionThread;
import com.nsystem.domain.executor.ThreadExecutor;
import com.nsystem.domain.model.Favourite;
import com.nsystem.domain.repository.MovieRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class UnFavourite extends UseCase<Integer, UnFavourite.Params> {

    private final MovieRepository movieRepository;

    @Inject
    public UnFavourite(ThreadExecutor threadExecutor,
                       PostExecutionThread postExecutionThread,
                       MovieRepository movieRepository) {
        super(threadExecutor, postExecutionThread);
        this.movieRepository = movieRepository;
    }

    @Override
    public Observable<Integer> buildUseCaseObservable(Params params) {
        return this.movieRepository.unFavourite(params.favourite);
    }

    public static final class Params {
        private final Favourite favourite;

        public Params(Favourite favourite) {
            this.favourite = favourite;
        }

        public static Params forFavouriteMovie(Favourite favourite) {
            return new Params(favourite);
        }
    }
}
