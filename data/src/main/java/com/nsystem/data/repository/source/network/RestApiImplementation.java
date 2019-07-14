package com.nsystem.data.repository.source.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.nsystem.data.entity.MovieEntity;
import com.nsystem.data.mapper.MovieEntityJsonMapper;
import com.nsystem.data.mapper.TrailerEntityJsonMapper;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;

public class RestApiImplementation implements RestApi{

    private final Context context;
    private final MovieEntityJsonMapper movieEntityJsonMapper;
    private final TrailerEntityJsonMapper trailerEntityJsonMapper;

    public RestApiImplementation(Context context,
                                 MovieEntityJsonMapper movieEntityJsonMapper,
                                 TrailerEntityJsonMapper trailerEntityJsonMapper) {
        if (context == null ||
                movieEntityJsonMapper == null ||
                trailerEntityJsonMapper == null) {
            throw new IllegalArgumentException("No construftor and mapper found for movie network connection...");
        }
        this.context = context;
        this.movieEntityJsonMapper = movieEntityJsonMapper;
        this.trailerEntityJsonMapper = trailerEntityJsonMapper;
    }

    @Override
    public Observable<List<MovieEntity>> popularMovieEntityList() {
        return Observable.create(emitter -> {
            if (isThereInternetConnection()) {
                try {
                    String responseMovieEntities = getPopularMovieEntitiesFromApi();
                    if (responseMovieEntities != null) {
                        emitter.onNext(movieEntityJsonMapper.transformMovieEntityCollection(
                                responseMovieEntities));
                        emitter.onComplete();
                    } else {
                        emitter.onError(new Exception());
                    }
                } catch (Exception e) {
                    emitter.onError(new Exception(e.getCause()));
                }
            } else {
                emitter.onError(new Exception());
            }
        });
    }

    @Override
    public Observable<List<MovieEntity>> topRatedMovieEntityList() {
        return Observable.create(emitter -> {
            if (isThereInternetConnection()) {
                try {
                    String responseMovieEntities = getTopRatedMovieEntitiesFromApi();
                    if (responseMovieEntities != null) {
                        emitter.onNext(movieEntityJsonMapper.transformMovieEntityCollection(
                                responseMovieEntities));
                        emitter.onComplete();
                    } else {
                        emitter.onError(new Exception());
                    }
                } catch (Exception e) {
                    emitter.onError(new Exception(e.getCause()));
                }
            } else {
                emitter.onError(new Exception());
            }
        });
    }

    @Override
    public Observable<MovieEntity> movieEntity(int movieId) {
        return Observable.create(emitter -> {
            if (isThereInternetConnection()) {
                try {
                    String responseMovieDetails = getMovieDetailsFromApi(movieId);
                    if (responseMovieDetails != null) {
                        emitter.onNext(movieEntityJsonMapper.transformMovieEntity(responseMovieDetails));
                        emitter.onComplete();
                    } else {
                        emitter.onError(new Exception());
                    }
                } catch (Exception e) {
                    emitter.onError(new Exception(e.getCause()));
                }
            } else {
                emitter.onError(new Exception());
            }
        }).flatMap(this::getTrailerEntity);
    }

    private Observable<MovieEntity> getTrailerEntity(final Object object) {
        return Observable.create(emitter -> {
           if (isThereInternetConnection()) {
               try {
                   MovieEntity movieEntity = (MovieEntity) object;
                   String responseTrailerList = getTrailerList(movieEntity.getMovieId());
                   if (responseTrailerList != null) {
                       movieEntity.setTrailers(trailerEntityJsonMapper.transformTrailerEntityCollection(responseTrailerList));

                       emitter.onNext(movieEntity);
                       emitter.onComplete();
                   } else {
                       emitter.onError(new Exception());
                   }
               } catch (Exception e) {
                   emitter.onError(new Exception(e.getCause()));
               }
           } else {
               emitter.onError(new Exception());
           }
        });
    }

    private String getPopularMovieEntitiesFromApi() throws MalformedURLException {
        String apiUrl = String.format(Locale.ENGLISH, API_POPULAR_URL, "en-US", 1);
        return ApiConnection.createGET(apiUrl).requestSyncCall();
    }

    private String getTopRatedMovieEntitiesFromApi() throws MalformedURLException {
        String apiUrl = String.format(Locale.ENGLISH, API_TOP_RATED_URL, "en-US", 1);
        return ApiConnection.createGET(apiUrl).requestSyncCall();
    }

    private String getMovieDetailsFromApi(int movieId) throws MalformedURLException {
        String apiUrl = String.format(Locale.ENGLISH, API_MOVIE_DETAILS_URL, movieId, "en-US", 1);
        return ApiConnection.createGET(apiUrl).requestSyncCall();
    }

    private String getTrailerList(int movieId) throws MalformedURLException {
        String apiUrl = String.format(Locale.ENGLISH, API_TRAILER_URL, movieId, "en-US");
        return ApiConnection.createGET(apiUrl).requestSyncCall();
    }

    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) return false;

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }
}
