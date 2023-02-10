package com.nashss.se.realdeal.dependency;

import javax.inject.Singleton;

import com.amazonaws.services.lambda.runtime.RequestHandler;
<<<<<<< HEAD
import com.nashss.se.realdeal.activity.GetAllMovieReviewsActivity;
import com.nashss.se.realdeal.activity.GetMovieActivity;
import com.nashss.se.realdeal.activity.GetReviewActivity;
import com.nashss.se.realdeal.activity.requests.GetAllMovieReviewsRequest;
=======
import com.nashss.se.realdeal.activity.CreateMovieActivity;
import com.nashss.se.realdeal.activity.GetMovieActivity;
import com.nashss.se.realdeal.activity.GetReviewActivity;
import com.nashss.se.realdeal.activity.requests.CreateMovieRequest;
>>>>>>> 22eac228ff67e8a6234ca483d820264afec78729
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
<<<<<<< HEAD
     * @return GetAllMovieReviewsActivity
     */
    GetAllMovieReviewsActivity provideGetAllMovieReviewsActivity();
=======
     * @return CreateMovieActivity
     */
    CreateMovieActivity provideCreateMovieActivity();
>>>>>>> 22eac228ff67e8a6234ca483d820264afec78729
}
