package com.nsystem.ntheatre.presenter;

import androidx.annotation.NonNull;

import com.nsystem.domain.interactor.GetFavourite;
import com.nsystem.domain.model.Favourite;
import com.nsystem.ntheatre.internal.di.PerActivity;
import com.nsystem.ntheatre.mapper.FavouriteModelDataMapper;
import com.nsystem.ntheatre.model.FavouriteModel;
import com.nsystem.ntheatre.view.FavouriteListView;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

@PerActivity
public class FavouriteMovieListPresenter implements Presenter{

    private FavouriteListView viewListView;

    private final GetFavourite getFavouriteMovieListUseCase;
    private final FavouriteModelDataMapper favouriteModelDataMapper;

    @Inject
    public FavouriteMovieListPresenter(GetFavourite getFavouriteMovieListUseCase,
                                       FavouriteModelDataMapper favouriteModelDataMapper) {
        this.getFavouriteMovieListUseCase = getFavouriteMovieListUseCase;
        this.favouriteModelDataMapper = favouriteModelDataMapper;
    }

    public void setView(@NonNull FavouriteListView view) {
        this.viewListView = view;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    public void onMovieClicked(FavouriteModel favouriteModel) {
        this.viewListView.viewFavourite(favouriteModel);
    }

    @Override
    public void destroy() {
        this.getFavouriteMovieListUseCase.dispose();
        this.viewListView = null;
    }

    public void initialize() {
        this.loadMovieList();
    }

    private void loadMovieList() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getFavouriteMovieList();
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

    private void showFavouriteMovieCollectionInView(Collection<Favourite> favouriteCollection) {
        if (favouriteCollection.size() <= 0) {
            this.hideViewLoading();
            this.showViewRetry();
            this.viewListView.showError("You have no favourite movie... sad :'(");
        }

        final Collection<FavouriteModel> favouriteModelCollection =
                this.favouriteModelDataMapper.transform(favouriteCollection);
        this.viewListView.renderFavouriteList(favouriteModelCollection);
    }

    private void getFavouriteMovieList() {
        this.getFavouriteMovieListUseCase.execute(new FavouriteListObserver(), null);
    }

    private final class FavouriteListObserver extends DisposableObserver<List<Favourite>> {

        @Override
        public void onNext(List<Favourite> favourites) {
            FavouriteMovieListPresenter.this.showFavouriteMovieCollectionInView(favourites);
            FavouriteMovieListPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            FavouriteMovieListPresenter.this.hideViewLoading();
            FavouriteMovieListPresenter.this.showErrorMessage(e.getMessage());
            FavouriteMovieListPresenter.this.showViewRetry();
        }

        @Override
        public void onComplete() {
            FavouriteMovieListPresenter.this.hideViewLoading();
        }
    }
}
