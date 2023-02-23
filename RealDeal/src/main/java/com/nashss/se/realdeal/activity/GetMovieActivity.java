package com.nashss.se.realdeal.activity;

import com.nashss.se.realdeal.activity.requests.GetMovieRequest;
import com.nashss.se.realdeal.activity.results.GetMovieResult;
import com.nashss.se.realdeal.dynamodb.DAO.MoviesDAO;
import com.nashss.se.realdeal.dynamodb.models.Movies;
import com.nashss.se.realdeal.exception.ReviewNotFoundException;

import javax.inject.Inject;


public class GetMovieActivity {
    private final MoviesDAO moviesDAO;

    @Inject
    public GetMovieActivity(MoviesDAO moviesDAO) {
        this.moviesDAO = moviesDAO;
    }

    public GetMovieResult handleRequest(final GetMovieRequest getMovieRequest) {
        String movieId = getMovieRequest.getId();

        Movies singleMovie = moviesDAO.getMovie(movieId);

        if (singleMovie == null) {
            throw new ReviewNotFoundException("Review not found with id " + movieId);
        }

        return GetMovieResult.builder()
                .withMovie(singleMovie)
                .build();

    }
}
