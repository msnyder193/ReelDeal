package com.nashss.se.realdeal.dependency;

import javax.inject.Singleton;

import com.nashss.se.realdeal.activity.CreateMovieActivity;
import com.nashss.se.realdeal.activity.CreateReviewActivity;
import com.nashss.se.realdeal.activity.DeleteReviewActivity;
import com.nashss.se.realdeal.activity.GetAllMovieReviewsActivity;
import com.nashss.se.realdeal.activity.GetAllMoviesActivity;
import com.nashss.se.realdeal.activity.GetMovieActivity;
import com.nashss.se.realdeal.activity.GetReviewActivity;

import com.nashss.se.realdeal.activity.UpdateMovieActivity;
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

    /**
     * provides the relevant activity.
     * @return GetAllMovieReviewsActivity
     */
    GetAllMovieReviewsActivity provideGetAllMovieReviewsActivity();

    /**
     * provides the relevant activity.
     * @return UpdateMovieActivity
     */
    UpdateMovieActivity provideUpdateMovieActivity();

    /**
     * provides the relevant activity.
     * @return CreateMovieActivity
     */
    CreateMovieActivity provideCreateMovieActivity();

    /**
     * provides the relevant activity.
     * @return CreateReviewActivity
     */
    CreateReviewActivity provideCreateReviewActivity();

    /**
     *
     * @return DeleteReviewActivity
     */
    DeleteReviewActivity provideDeleteReviewActivity();
}
