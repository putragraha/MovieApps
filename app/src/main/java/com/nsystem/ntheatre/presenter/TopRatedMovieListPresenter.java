package com.nsystem.ntheatre.presenter;

import androidx.annotation.NonNull;

import com.nsystem.domain.interactor.GetTopRated;
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
public class TopRatedMovieListPresenter implements Presenter{

    private MovieListView viewListView;

    private final GetTopRated getTopRatedMovieListUseCase;
    private final MovieModelDataMapper movieModelDataMapper;

    @Inject
    public TopRatedMovieListPresenter(GetTopRated getTopRatedMovieListUseCase,
                                      MovieModelDataMapper movieModelDataMapper) {
        this.getTopRatedMovieListUseCase = getTopRatedMovieListUseCase;
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
        this.getTopRatedMovieListUseCase.dispose();
        this.viewListView = null;
    }

    public void initialize() {
        this.loadMovieList();
    }

    private void loadMovieList() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getTopRatedMovieList();
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

    private void showTopRatedMovieCollectionInView(Collection<Movie> movieCollection) {
        final Collection<MovieModel> movieModelCollection =
                this.movieModelDataMapper.transform(movieCollection);
        this.viewListView.renderMovieList(movieModelCollection);
    }

    private void getTopRatedMovieList() {
        this.getTopRatedMovieListUseCase.execute(new TopRatedMovieListObserver(), null);
    }

    private final class TopRatedMovieListObserver extends DisposableObserver<List<Movie>> {

        @Override
        public void onNext(List<Movie> movies) {
            TopRatedMovieListPresenter.this.showTopRatedMovieCollectionInView(movies);
        }

        @Override
        public void onError(Throwable e) {
            TopRatedMovieListPresenter.this.hideViewLoading();
            TopRatedMovieListPresenter.this.showErrorMessage(e.getMessage());
            TopRatedMovieListPresenter.this.showViewRetry();
        }

        @Override
        public void onComplete() {
            TopRatedMovieListPresenter.this.hideViewLoading();
        }
    }
}
