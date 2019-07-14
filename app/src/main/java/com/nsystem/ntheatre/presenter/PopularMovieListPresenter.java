package com.nsystem.ntheatre.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.nsystem.domain.interactor.GetPopular;
import com.nsystem.domain.model.Movie;
import com.nsystem.ntheatre.internal.di.PerActivity;
import com.nsystem.ntheatre.mapper.MovieModelDataMapper;
import com.nsystem.ntheatre.model.MovieModel;
import com.nsystem.ntheatre.view.MovieListView;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

@PerActivity
public class PopularMovieListPresenter implements Presenter{

    private MovieListView viewListView;

    private final GetPopular getPopularMovieListUseCase;
    private final MovieModelDataMapper movieModelDataMapper;

    @Inject
    public PopularMovieListPresenter(GetPopular getPopularMovieListUseCase,
                                     MovieModelDataMapper movieModelDataMapper) {
        this.getPopularMovieListUseCase = getPopularMovieListUseCase;
        this.movieModelDataMapper = movieModelDataMapper;
    }

    public void setView(@NonNull MovieListView view) {
        this.viewListView = view;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    public void onMovieClicked(MovieModel movieModel) {
        this.viewListView.viewMovie(movieModel);
    }

    @Override
    public void destroy() {
        this.getPopularMovieListUseCase.dispose();
        this.viewListView = null;
    }

    public void initialize() {
        this.loadMovieList();
    }

    private void loadMovieList() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getPopularMovieList();
    }

    private void showViewRetry() {
        this.viewListView.showRetry();
    }

    private void hideViewRetry() {
        this.viewListView.hideRetry();
    }

    private void showViewLoading() {
        this.viewListView.showLoading();
    }

    private void hideViewLoading() {
        this.viewListView.hideLoading();
    }

    private void showErrorMessage(String errorMessage) {
        this.viewListView.showError(errorMessage);
    }

    private void showPopularMovieCollectionInView(Collection<Movie> movieCollection) {
        final Collection<MovieModel> movieModelCollection =
                this.movieModelDataMapper.transform(movieCollection);
        this.viewListView.renderMovieList(movieModelCollection);
    }

    private void getPopularMovieList() {
        this.getPopularMovieListUseCase.execute(new PopularMovieListObserver(), null);
    }

    private final class PopularMovieListObserver extends DisposableObserver<List<Movie>> {

        @Override
        public void onNext(List<Movie> movies) {
            PopularMovieListPresenter.this.showPopularMovieCollectionInView(movies);
        }

        @Override
        public void onError(Throwable e) {
            PopularMovieListPresenter.this.hideViewLoading();
            PopularMovieListPresenter.this.showErrorMessage(e.getMessage());
            PopularMovieListPresenter.this.showViewRetry();
        }

        @Override
        public void onComplete() {
            PopularMovieListPresenter.this.hideViewLoading();
        }
    }
}
