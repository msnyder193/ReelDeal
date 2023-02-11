package com.nashss.se.realdeal.dynamodb.DAO;

import javax.inject.Inject;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.nashss.se.realdeal.dynamodb.models.Movies;
import com.nashss.se.realdeal.exception.MovieNotFoundException;
import com.nashss.se.realdeal.metrics.MetricsConstants;
import com.nashss.se.realdeal.metrics.MetricsPublisher;

public class MoviesDAO {
    private final DynamoDBMapper mapper;
    private final MetricsPublisher metricsPublisher;
    @Inject
    public MoviesDAO(DynamoDBMapper mapper, MetricsPublisher metricsPublisher) {
        this.mapper = mapper;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Makes a DynamoDB call to retrieve the specified movie.
     *
     * @param movieId the parameter that indicates the specified movie.
     * @return the movie Object retrieved from DynamoDB.
     */
    public Movies getMovie(String movieId) {
        Movies movie = this.mapper.load(Movies.class, movieId);
        if (movie == null) {
            metricsPublisher.addCount(MetricsConstants.GETMOVIE_MOVIENOTFOUND_COUNT, 1);
            throw new MovieNotFoundException("Could not find transaction with id: " + movieId);
        }
        metricsPublisher.addCount(MetricsConstants.GETMOVIE_MOVIENOTFOUND_COUNT, 0);
        return movie;

    }

    public List<Movies> getAllMovies() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return mapper.scan(Movies.class, scanExpression);
    }
    
    public void saveMovie(Movies movie) {
        this.mapper.save(movie);
    }

}