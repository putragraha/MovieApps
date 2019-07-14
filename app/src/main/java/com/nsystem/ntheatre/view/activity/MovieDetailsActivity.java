package com.nsystem.ntheatre.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.nsystem.ntheatre.R;
import com.nsystem.ntheatre.internal.di.HasComponent;
import com.nsystem.ntheatre.internal.di.components.DaggerMovieComponent;
import com.nsystem.ntheatre.internal.di.components.MovieComponent;
import com.nsystem.ntheatre.view.fragment.MovieDetailsFragment;

public class MovieDetailsActivity extends BaseActivity implements
        HasComponent<MovieComponent> {

    private static final String INTENT_EXTRA_PARAM_MOVIE_ID = "com.nsystem.INTENT_PARAM_MOVIE_ID";
    private static final String INSTANCE_STATE_PARAM_MOVIE_ID = "com.nsystem.STATE_PARAM_MOVIE_ID";

    public static Intent getCallingIntent(Context context, int movieId) {
        Intent callingIntent = new Intent(context, MovieDetailsActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_MOVIE_ID, movieId);
        return callingIntent;
    }

    private int movieId;
    private MovieComponent movieComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_layout);

        this.initializeActivity(savedInstanceState);
        this.initializeInjector();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putInt(INSTANCE_STATE_PARAM_MOVIE_ID, this.movieId);
        }
        super.onSaveInstanceState(outState);
    }

    private void initializeActivity(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.movieId = getIntent().getIntExtra(INTENT_EXTRA_PARAM_MOVIE_ID, -1);
            addFragment(R.id.fragmentContainer, MovieDetailsFragment.forMovie(movieId));
        } else {
            this.movieId = savedInstanceState.getInt(INSTANCE_STATE_PARAM_MOVIE_ID);
        }
    }

    private void initializeInjector() {
        this.movieComponent = DaggerMovieComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public MovieComponent getComponent() {
        return movieComponent;
    }
}
