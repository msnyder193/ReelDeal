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
import com.nashss.se.realdeal.activity.CreateReviewActivity;
import com.nashss.se.realdeal.activity.GetMovieActivity;
import com.nashss.se.realdeal.activity.GetReviewActivity;
import com.nashss.se.realdeal.activity.requests.CreateMovieRequest;
<<<<<<< HEAD
import com.nashss.se.realdeal.activity.requests.CreateReviewRequest;
=======
>>>>>>> 22eac228ff67e8a6234ca483d820264afec78729
>>>>>>> 24f5d4eaca224a30576badcb0b34e34b20d228a0
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
     * @return GetAllMovieReviewsActivity
     */
    GetAllMovieReviewsActivity provideGetAllMovieReviewsActivity();

    /** 
     * provides the relevant activity.
     * @return CreateMovieActivity
     */
    CreateMovieActivity provideCreateMovieActivity();


    /**
     * Provides the relevant activity.
     * @return CreateReviewActivity
     */
    CreateReviewActivity provideCreateReviewActivity();

}
