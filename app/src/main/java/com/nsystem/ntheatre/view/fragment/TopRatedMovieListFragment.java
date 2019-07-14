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
import com.nsystem.ntheatre.model.MovieModel;
import com.nsystem.ntheatre.presenter.TopRatedMovieListPresenter;
import com.nsystem.ntheatre.view.MovieListView;
import com.nsystem.ntheatre.view.adapter.MoviesAdapter;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TopRatedMovieListFragment extends BaseFragment implements MovieListView {
    public interface MovieListListener {
        void onMovieClicked(final MovieModel movieModel);
    }

    @Inject
    TopRatedMovieListPresenter topRatedMovieListPresenter;
    @Inject
    MoviesAdapter moviesAdapter;

    @BindView(R.id.view_movie_list)
    RecyclerView viewMovieList;
    @BindView(R.id.view_loading)
    RelativeLayout viewLoading;
    @BindView(R.id.view_retry)
    RelativeLayout viewRetry;
    @BindView(R.id.btn_retry)
    Button btnRetry;

    private TopRatedMovieListFragment.MovieListListener movieListListener;
    private Unbinder binder;

    public TopRatedMovieListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof TopRatedMovieListFragment.MovieListListener) {
            this.movieListListener = (TopRatedMovieListFragment.MovieListListener) activity;
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
        this.topRatedMovieListPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadMovieList();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.topRatedMovieListPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.topRatedMovieListPresenter.pause();
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
        this.topRatedMovieListPresenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.movieListListener = null;
    }

    @Override
    public void renderMovieList(Collection<MovieModel> movieModelCollection) {
        if (movieModelCollection != null) {
            this.moviesAdapter.setMoviesCollection(movieModelCollection);
        }
    }

    @Override
    public void viewMovie(MovieModel movieModel) {
        if (this.topRatedMovieListPresenter != null) {
            this.movieListListener.onMovieClicked(movieModel);
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

    private void loadMovieList() {
        this.topRatedMovieListPresenter.initialize();
    }

    @OnClick(R.id.btn_retry) void onButtonRetryClick() {
        TopRatedMovieListFragment.this.loadMovieList();
    }


    private void setupRecyclerView() {
        this.moviesAdapter.setOnItemClickListener(movieModel -> {
            if (TopRatedMovieListFragment.this.topRatedMovieListPresenter != null && movieModel != null) {
                TopRatedMovieListFragment.this.topRatedMovieListPresenter.onMovieClicked(movieModel);
            }
        });
        this.viewMovieList.setLayoutManager(new GridLayoutManager(context(), 2));
        this.viewMovieList.setAdapter(moviesAdapter);
    }
}
