package com.nsystem.ntheatre.view.activity;

import android.os.Bundle;
import android.os.Handler;

import com.nsystem.ntheatre.R;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() ->
                SplashActivity.this.navigator.navigateToPopularMovieList(
                SplashActivity.this
        ), 2000);
    }

}
