package com.nsystem.domain.interactor;

import com.nsystem.domain.executor.PostExecutionThread;
import com.nsystem.domain.UseCase;
import com.nsystem.domain.executor.ThreadExecutor;
import com.nsystem.domain.model.Movie;
import com.nsystem.domain.repository.MovieRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetTopRated extends UseCase<List<Movie>, Void> {

    private final MovieRepository movieRepository;

    @Inject
    GetTopRated(MovieRepository movieRepository, ThreadExecutor threadExecutor,
                PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.movieRepository = movieRepository;
    }


    @Override
    public Observable<List<Movie>> buildUseCaseObservable(Void aVoid) {
        return this.movieRepository.topRatedMovies();
    }
}
