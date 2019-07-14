package com.nsystem.ntheatre.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.nsystem.ntheatre.R;
import com.nsystem.ntheatre.internal.di.HasComponent;
import com.nsystem.ntheatre.internal.di.components.DaggerMovieComponent;
import com.nsystem.ntheatre.internal.di.components.MovieComponent;
import com.nsystem.ntheatre.model.MovieModel;
import com.nsystem.ntheatre.view.fragment.TopRatedMovieListFragment;

public class TopRatedMovieListActivity extends BaseActivity implements
        HasComponent<MovieComponent>, TopRatedMovieListFragment.MovieListListener {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, TopRatedMovieListActivity.class);
    }

    private MovieComponent movieComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_layout);

        this.initializeInjector();
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new TopRatedMovieListFragment());
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

    @Override
    public void onMovieClicked(MovieModel movieModel) {
        this.navigator.navigateToMovieDetails(this, movieModel.getMovieId());
    }
}
