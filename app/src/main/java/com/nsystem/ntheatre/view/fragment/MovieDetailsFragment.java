package com.nsystem.ntheatre.view.fragment;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nsystem.ntheatre.R;
import com.nsystem.ntheatre.internal.di.components.MovieComponent;
import com.nsystem.ntheatre.model.MovieModel;
import com.nsystem.ntheatre.model.TrailerModel;
import com.nsystem.ntheatre.presenter.MovieDetailsPresenter;
import com.nsystem.ntheatre.util.ConvertDate;
import com.nsystem.ntheatre.view.MovieDetailsView;
import com.nsystem.ntheatre.view.adapter.TrailerAdapter;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MovieDetailsFragment extends BaseFragment implements MovieDetailsView {
    private static final String PARAM_MOVIE_ID = "param_movie_id";

    @Inject MovieDetailsPresenter movieDetailsPresenter;
    @Inject TrailerAdapter trailerAdapter;

    @BindView(R.id.tv_title) TextView textViewTitle;
    @BindView(R.id.iv_details_poster) ImageView imageViewPoster;
    @BindView(R.id.tv_release_date) TextView textViewReleaseDate;
    @BindView(R.id.tv_duration) TextView textViewDuration;
    @BindView(R.id.tv_popularity) TextView textViewPopularity;
    @BindView(R.id.btn_details_favourite) ToggleButton btnFavourite;
    @BindView(R.id.tv_overview) TextView textViewOverview;
    @BindView(R.id.view_trailer_list) RecyclerView viewTrailerList;

    @BindView(R.id.view_loading) RelativeLayout viewLoading;
    @BindView(R.id.view_retry) RelativeLayout viewRetry;
    @BindView(R.id.btn_retry) Button btnRetry;

    private Unbinder binder;

    public static MovieDetailsFragment forMovie(int movieId) {
        final MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment();
        final Bundle args = new Bundle();
        args.putInt(PARAM_MOVIE_ID, movieId);
        movieDetailsFragment.setArguments(args);
        return movieDetailsFragment;
    }

    public MovieDetailsFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(MovieComponent.class).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_movie_details, container, false);
        binder = ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.movieDetailsPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadMovieDetails();
        }
    }

    @Override public void onResume() {
        super.onResume();
        this.movieDetailsPresenter.resume();
    }

    @Override public void onPause() {
        super.onPause();
        this.movieDetailsPresenter.pause();
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        binder.unbind();
    }

    @Override public void onDestroy() {
        super.onDestroy();
        this.movieDetailsPresenter.destroy();
    }

    @Override
    public void renderMovie(MovieModel movieModel) {
        if (movieModel != null) {
            try {
                String duration = movieModel.getDuration() + " min";
                String releaseDate = ConvertDate.to(movieModel.getReleaseDate(),
                        ConvertDate.YYYY_MM_DD,
                        ConvertDate.MMM_DD_COMMA_YYYY);
                String popularity = movieModel.getPopularity() + "/10";

                this.textViewTitle.setText(movieModel.getOriginalTitle());
                Picasso.get().load(movieModel.getPosterPath()).into(this.imageViewPoster);
                this.textViewReleaseDate.setText(releaseDate);
                this.textViewDuration.setText(duration);
                this.textViewPopularity.setText(popularity);
                this.textViewOverview.setText(movieModel.getOverview());
                setupRecyclerView(movieModel.getTrailerModelList());
            } catch (ParseException parseException) {
                showToastMessage(parseException.getMessage());
            }
        }
    }

    @Override
    public void openTrailer(TrailerModel trailerModel) {
        String youtubeKey = trailerModel.getKey();

        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + youtubeKey));
        appIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + youtubeKey));
        webIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        try {
            context().startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context().startActivity(webIntent);
        }
    }

    private void setupRecyclerView(List<TrailerModel> trailerModelList) {
        this.trailerAdapter.setTrailerCollection(trailerModelList);
        this.trailerAdapter.setOnItemClickListener(trailerModel -> {
            if (MovieDetailsFragment.this.movieDetailsPresenter != null && trailerModel != null) {
                MovieDetailsFragment.this.movieDetailsPresenter.onTrailerClicked(trailerModel);
            }
        });
        this.viewTrailerList.setLayoutManager(new LinearLayoutManager(context()));
        this.viewTrailerList.setAdapter(this.trailerAdapter);
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
        return getActivity().getApplicationContext();
    }

    private void loadMovieDetails() {
        if (this.movieDetailsPresenter != null) {
            this.movieDetailsPresenter.initialize(currentMovieId());
        }
    }

    private int currentMovieId() {
        final Bundle args = getArguments();
        return args.getInt(PARAM_MOVIE_ID);
    }

    @OnClick(R.id.btn_retry)
    void onButtonRetryClick() {
        MovieDetailsFragment.this.loadMovieDetails();
    }
}
