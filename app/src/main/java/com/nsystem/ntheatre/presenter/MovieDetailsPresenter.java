package com.nsystem.ntheatre.presenter;

import androidx.annotation.NonNull;

import com.nsystem.domain.interactor.GetMovieDetails;
import com.nsystem.domain.model.Movie;
import com.nsystem.ntheatre.internal.di.PerActivity;
import com.nsystem.ntheatre.mapper.MovieModelDataMapper;
import com.nsystem.ntheatre.model.MovieModel;
import com.nsystem.ntheatre.view.MovieDetailsView;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

@PerActivity
public class MovieDetailsPresenter implements Presenter {

    private MovieDetailsView viewDetailsView;

    private final GetMovieDetails getMovieDetailsUseCase;
    private final MovieModelDataMapper movieModelDataMapper;

    @Inject
    public MovieDetailsPresenter(GetMovieDetails getMovieDetailsUseCase,
                                 MovieModelDataMapper movieModelDataMapper) {
        this.getMovieDetailsUseCase = getMovieDetailsUseCase;
        this.movieModelDataMapper = movieModelDataMapper;
    }

    public void setView(@NonNull MovieDetailsView view) {
        this.viewDetailsView  = view;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.getMovieDetailsUseCase.dispose();
        this.viewDetailsView = null;
    }

    public void initialize(int movieId) {
        this.hideViewRetry();
        this.showViewLoading();
        this.getMovieDetails(movieId);
    }

    private void showViewRetry() {
        this.viewDetailsView.showRetry();
    }

    private void hideViewRetry() {
        this.viewDetailsView.hideRetry();
    }

    private void showViewLoading() {
        this.viewDetailsView.showLoading();
    }

    private void hideViewLoading() {
        this.viewDetailsView.hideLoading();
    }

    private void showErrorMessage(String message) {
        this.viewDetailsView.showError(message);
    }

    private void getMovieDetails(int movieId) {
        this.getMovieDetailsUseCase.execute(new MovieDetailsObserver(), GetMovieDetails.Params.forUser(movieId));
    }

    private void showMovieDetailsInView(Movie movie) {
        final MovieModel movieModel = this.movieModelDataMapper.transform(movie);
        this.viewDetailsView.renderMovie(movieModel);
    }

    private final class MovieDetailsObserver extends DisposableObserver<Movie> {

        @Override
        public void onNext(Movie movie) {
            MovieDetailsPresenter.this.showMovieDetailsInView(movie);
        }

        @Override
        public void onError(Throwable e) {
            MovieDetailsPresenter.this.hideViewLoading();
            MovieDetailsPresenter.this.showErrorMessage(e.getMessage());
            MovieDetailsPresenter.this.showViewRetry();
        }

        @Override
        public void onComplete() {
            MovieDetailsPresenter.this.hideViewLoading();
        }
    }
}
