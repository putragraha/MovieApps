package com.nsystem.domain.interactor;

import com.nsystem.domain.executor.PostExecutionThread;
import com.nsystem.domain.UseCase;
import com.nsystem.domain.executor.ThreadExecutor;
import com.nsystem.domain.model.Movie;
import com.nsystem.domain.repository.MovieRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetMovieDetails extends UseCase<Movie, GetMovieDetails.Params> {

    private final MovieRepository movieRepository;

    @Inject
    public GetMovieDetails(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, MovieRepository movieRepository) {
        super(threadExecutor, postExecutionThread);
        this.movieRepository = movieRepository;
    }

    @Override
    public Observable<Movie> buildUseCaseObservable(GetMovieDetails.Params params) {
        return this.movieRepository.movie(params.movieId);
    }

    public static final class Params {
        private final int movieId;

        Params(int movieId) {
            this.movieId = movieId;
        }

        public static Params forUser(int movieId) {
            return new Params(movieId);
        }
    }
}
