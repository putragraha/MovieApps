package com.nsystem.ntheatre.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nsystem.ntheatre.R;
import com.nsystem.ntheatre.internal.di.components.MovieComponent;
import com.nsystem.ntheatre.model.FavouriteModel;
import com.nsystem.ntheatre.presenter.FavouriteMovieListPresenter;
import com.nsystem.ntheatre.view.FavouriteListView;
import com.nsystem.ntheatre.view.adapter.FavouriteMoviesAdapter;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FavouriteMovieListFragment extends BaseFragment implements FavouriteListView {

    public interface FavouriteListListener {
        void onMovieClicked(final FavouriteModel favouriteModel);
    }

    @Inject
    FavouriteMovieListPresenter favouriteMovieListPresenter;
    @Inject
    FavouriteMoviesAdapter favouriteMoviesAdapter;

    @BindView(R.id.view_movie_list)
    RecyclerView viewMovieList;
    @BindView(R.id.view_loading)
    RelativeLayout viewLoading;
    @BindView(R.id.view_retry)
    RelativeLayout viewRetry;
    @BindView(R.id.btn_retry)
    Button btnRetry;

    private FavouriteListListener favouriteListListener;
    private Unbinder binder;

    public FavouriteMovieListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof FavouriteListListener) {
            this.favouriteListListener = (FavouriteListListener) activity;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(MovieComponent.class).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_movie_list, container, false);
        binder = ButterKnife.bind(this, fragmentView);
        setupRecyclerView();
        return fragmentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.favouriteMovieListPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadFavouriteMovieList();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.favouriteMovieListPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.favouriteMovieListPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewMovieList.setAdapter(null);
        binder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.favouriteMovieListPresenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.favouriteListListener = null;
    }

    @Override
    public void renderFavouriteList(Collection<FavouriteModel> favouriteModelCollection) {
        if (favouriteModelCollection != null) {
            this.favouriteMoviesAdapter.setFavouriteMoviesCollection(favouriteModelCollection);
        }
    }

    @Override
    public void viewFavourite(FavouriteModel favouriteModel) {
        if (this.favouriteMovieListPresenter != null) {
            this.favouriteListListener.onMovieClicked(favouriteModel);
        }
    }

    @Override
    public void showLoading() {
        this.viewLoading.setVisibility(View.VISIBLE);
        this.getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void hideLoading() {
        this.viewLoading.setVisibility(View.GONE);
        this.getActivity().setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void showRetry() {
        this.viewRetry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        this.viewRetry.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context context() {
        return this.getActivity().getApplicationContext();
    }

    private void loadFavouriteMovieList() {
        this.favouriteMovieListPresenter.initialize();
    }

    @OnClick(R.id.btn_retry) void onButtonRetryClick() {
        FavouriteMovieListFragment.this.loadFavouriteMovieList();
    }

    private void setupRecyclerView() {
        this.favouriteMoviesAdapter.setOnItemClickListener(movieModel -> {
            if (FavouriteMovieListFragment.this.favouriteMovieListPresenter != null && movieModel != null) {
                FavouriteMovieListFragment.this.favouriteMovieListPresenter.onMovieClicked(movieModel);
            }
        });
        this.viewMovieList.setLayoutManager(new GridLayoutManager(context(), 2));
        this.viewMovieList.setAdapter(favouriteMoviesAdapter);
    }
}
