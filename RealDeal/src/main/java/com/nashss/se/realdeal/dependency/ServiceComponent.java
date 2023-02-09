package com.nashss.se.realdeal.dependency;

import javax.inject.Singleton;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.realdeal.activity.GetAllMoviesActivity;
import com.nashss.se.realdeal.activity.GetMovieActivity;
import com.nashss.se.realdeal.activity.GetReviewActivity;
import com.nashss.se.realdeal.activity.requests.GetAllMoviesRequest;
import dagger.Component;

/**
 * Dagger component for providing dependency injection in the Music Playlist Service.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    /**
     * Provides the relevant activity.
     * @return GetMovieActivity
     */
    GetMovieActivity provideGetMovieActivity();

    /**
     * Provides the relevant activity.
     * @return GetReviewActivity
     */
    GetReviewActivity provideGetReviewActivity();

    /**
     * provides the relevant activity.
     * @return GetAllMoviesActivity
     */
    GetAllMoviesActivity provideGetAllMoviesActivity();
}
