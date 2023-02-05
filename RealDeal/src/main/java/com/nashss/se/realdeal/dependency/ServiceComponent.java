package com.nashss.se.realdeal.dependency;

import com.nashss.se.realdeal.activity.GetMovieActivity;
import com.nashss.se.realdeal.activity.GetReviewActivity;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    /**
     * Provides the relevant activity.
     *
     * @return GetMovieActivity
     */
    GetMovieActivity provideGetMovieActivity();

    /**
     * Provides the relevant activity.
     *
     * @return GetReviewActivity
     */
    GetReviewActivity provideGetReviewActivity();
}