package com.nsystem.ntheatre.view.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.nsystem.ntheatre.AndroidApplication;
import com.nsystem.ntheatre.R;
import com.nsystem.ntheatre.internal.di.components.ApplicationComponent;
import com.nsystem.ntheatre.internal.di.modules.ActivityModule;
import com.nsystem.ntheatre.navigation.Navigator;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    void addFragment(int containerViewId, Fragment fragment) {
        final FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_popular:
                navigator.navigateToPopularMovieList(this);
                break;
            case R.id.menu_top_rated:
                navigator.navigateToTopRatedMovieList(this);
                break;
            case R.id.menu_favourite:
                navigator.navigateToFavouriteMovieList(this);
                break;
            default:
                break;
        }

        return true;
    }
}
