package com.nashss.se.realdeal.activity;

import javax.management.InvalidAttributeValueException;

import com.nashss.se.realdeal.activity.requests.UpdateMovieRequest;
import com.nashss.se.realdeal.activity.results.UpdateMovieResult;
import com.nashss.se.realdeal.converter.ModelConverter;
import com.nashss.se.realdeal.dynamodb.DAO.MoviesDAO;
import com.nashss.se.realdeal.dynamodb.models.Movies;
import com.nashss.se.realdeal.metrics.MetricsPublisher;
import com.nashss.se.realdeal.utils.RealDealServiceLambda;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateMovieActivity {
    private final Logger log = LogManager.getLogger();
    private final MoviesDAO moviesDAO;
    private final MetricsPublisher metricsPublisher;


    public UpdateMovieActivity(MoviesDAO moviesDAO, MetricsPublisher metricsPublisher) {
        this.moviesDAO = moviesDAO;
        this.metricsPublisher = metricsPublisher;
    }

    public UpdateMovieResult handleRequest(final UpdateMovieRequest request) {
        log.info("Received updateMovieRequest:" + request);

        if(!RealDealServiceLambda.isValidString(request.getId())) {
            try {
                throw new InvalidAttributeValueException("MovieId" + request.getId() +
                    "contains an illegal character");
            } catch (InvalidAttributeValueException e) {
                throw new RuntimeException(e);
            }
        }

        Movies movie = moviesDAO.getMovie(request.getId());

        if(!movie.getId().equals(request.getId())) {
            try {
                throw new InvalidAttributeValueException("Can not change Movie ID"
                    + movie.getId());
            } catch (InvalidAttributeValueException e) {
                throw new RuntimeException(e);
            }
        }

        movie.setTitle(request.getTitle());
        movie.setDescription(request.getDescription());
        movie.setDirector(request.getDirector());
        movie.setReleaseDate(request.getReleaseDate());
        movie.setPosterUrl(request.getPosterUrl());
        movie.setGenres(request.getGenres());
        movie.setCast(request.getCast());

        return UpdateMovieResult.builder()
            .withMovie(new ModelConverter().toMoviesModel(movie))
            .build();
    }
}
