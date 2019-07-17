package com.nsystem.ntheatre.presenter;

import androidx.annotation.NonNull;

import com.nsystem.domain.interactor.AddFavourite;
import com.nsystem.domain.interactor.GetMovieDetails;
import com.nsystem.domain.model.Favourite;
import com.nsystem.domain.model.Movie;
import com.nsystem.ntheatre.internal.di.PerActivity;
import com.nsystem.ntheatre.mapper.MovieModelDataMapper;
import com.nsystem.ntheatre.model.MovieModel;
import com.nsystem.ntheatre.model.TrailerModel;
import com.nsystem.ntheatre.view.MovieDetailsView;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

@PerActivity
public class MovieDetailsPresenter implements Presenter {

    private MovieDetailsView viewDetailsView;

    private final GetMovieDetails getMovieDetailsUseCase;
    private final AddFavourite addFavouriteUseCase;
    private final MovieModelDataMapper movieModelDataMapper;

    @Inject
    public MovieDetailsPresenter(GetMovieDetails getMovieDetailsUseCase,
                                 AddFavourite addFavourite,
                                 MovieModelDataMapper movieModelDataMapper) {
        this.getMovieDetailsUseCase = getMovieDetailsUseCase;
        this.addFavouriteUseCase = addFavourite;
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

    public void onTrailerClicked(TrailerModel trailerModel) {
        this.viewDetailsView.openTrailer(trailerModel);
    }

    public void addFavouriteMovie(Favourite favourite) {
        this.addFavouriteUseCase.execute(new AddFavouriteObserver(),
                AddFavourite.Params.forFavouriteMovie(favourite));
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
        this.getMovieDetailsUseCase.execute(new MovieDetailsObserver(), GetMovieDetails.Params.forMovie(movieId));
    }

    private void showMovieDetailsInView(Movie movie) {
        final MovieModel movieModel = this.movieModelDataMapper.transform(movie);
        this.viewDetailsView.renderMovie(movieModel);
    }

    private void toggleFavourite(boolean isActivated) {
        this.viewDetailsView.toggleFavourite(isActivated);
    }

    private void rollbackToggle() {
        this.viewDetailsView.rollbackToggle();
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

    private final class AddFavouriteObserver extends DisposableObserver<Long> {

        @Override
        public void onNext(Long aLong) {
            MovieDetailsPresenter.this.toggleFavourite(aLong > 0);
        }

        @Override
        public void onError(Throwable e) {
            MovieDetailsPresenter.this.rollbackToggle();
            MovieDetailsPresenter.this.showErrorMessage(e.getMessage());
        }

        @Override
        public void onComplete() {}
    }
}
