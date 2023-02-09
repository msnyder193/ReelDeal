package com.nashss.se.realdeal.dependency;

import javax.inject.Singleton;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.realdeal.activity.CreateMovieActivity;
import com.nashss.se.realdeal.activity.CreateReviewActivity;
import com.nashss.se.realdeal.activity.GetMovieActivity;
import com.nashss.se.realdeal.activity.GetReviewActivity;
import com.nashss.se.realdeal.activity.requests.CreateMovieRequest;
import com.nashss.se.realdeal.activity.requests.CreateReviewRequest;
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
     * Provides the relevant activity.
     * @return CreateMovieActivity
     */
    CreateMovieActivity provideCreateMovieActivity();

    /**
     * Provides the relevant activity.
     * @return CreateReviewActivity
     */
    CreateReviewActivity provideCreateReviewActivity();
}
