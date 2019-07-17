package com.nsystem.ntheatre.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.nsystem.ntheatre.R;
import com.nsystem.ntheatre.internal.di.HasComponent;
import com.nsystem.ntheatre.internal.di.components.DaggerMovieComponent;
import com.nsystem.ntheatre.internal.di.components.MovieComponent;
import com.nsystem.ntheatre.model.FavouriteModel;
import com.nsystem.ntheatre.view.fragment.FavouriteMovieListFragment;

public class FavouriteMovieListActivity extends BaseActivity implements
        HasComponent<MovieComponent>, FavouriteMovieListFragment.FavouriteListListener {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, FavouriteMovieListActivity.class);
    }

    private MovieComponent movieComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_layout);

        this.initializeInjector();
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new FavouriteMovieListFragment());
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
    public void onMovieClicked(FavouriteModel favouriteModel) {
        this.navigator.navigateToMovieDetails(this, favouriteModel.getMovieId());
    }
}
