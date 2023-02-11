package com.nashss.se.realdeal.activity;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import com.nashss.se.realdeal.activity.requests.GetAllMoviesRequest;
import com.nashss.se.realdeal.activity.results.GetAllMoviesResult;
import com.nashss.se.realdeal.converter.ModelConverter;
import com.nashss.se.realdeal.dynamodb.DAO.MoviesDAO;
import com.nashss.se.realdeal.dynamodb.models.Movies;
import com.nashss.se.realdeal.models.MoviesModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetAllMoviesActivity {
    private final Logger log = LogManager.getLogger();

    private final MoviesDAO moviesDao;

    @Inject
    public GetAllMoviesActivity(MoviesDAO movieDao) {
        this.moviesDao = movieDao;
    }

    public GetAllMoviesResult handleRequest(final GetAllMoviesRequest getAllMoviesRequest) {
        log.info("Received getAllMoviesRequest {}", getAllMoviesRequest);
        List<Movies> moviesList = moviesDao.getAllMovies();
        List<MoviesModel> moviesModelList = new ArrayList<>();

        for (Movies movie : moviesList) {
            MoviesModel moviesModel = new ModelConverter().toMoviesModel(movie);

        }
        return GetAllMoviesResult.builder()
            .withMovies(moviesModelList)
            .build();
    }
}