package com.nashss.se.realdeal.dependency;

import javax.inject.Singleton;

import com.nashss.se.realdeal.activity.GetMovieActivity;
import com.nashss.se.realdeal.activity.GetReviewActivity;
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

}
