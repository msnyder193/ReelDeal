package com.nashss.se.realdeal.activity;

import javax.inject.Inject;

import java.util.List;
import java.util.UUID;

import com.nashss.se.realdeal.activity.requests.CreateMovieRequest;
import com.nashss.se.realdeal.activity.results.CreateMovieResult;
import com.nashss.se.realdeal.converter.ModelConverter;
import com.nashss.se.realdeal.dynamodb.DAO.MoviesDAO;
import com.nashss.se.realdeal.dynamodb.models.Movies;
import com.nashss.se.realdeal.models.MoviesModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateMovieActivity {
    private final Logger log = LogManager.getLogger();
    private final MoviesDAO moviesDAO;

    @Inject
    public CreateMovieActivity(MoviesDAO moviesDAO) {
        this.moviesDAO = moviesDAO;
    }

    public CreateMovieResult handleRequest(final CreateMovieRequest createMovieRequest) {
        log.info("Recieved CreateMovieRequest {}", createMovieRequest);
        String movieId = createMovieId();
        String title = createMovieRequest.getTitle();
        String description = createMovieRequest.getDescription();
        String releaseDate = createMovieRequest.getReleaseDate();
        String posterUrl = createMovieRequest.getPosterUrl();
        List<String> genres = createMovieRequest.getGenres();
        List<String> cast = createMovieRequest.getCast();
        String director = createMovieRequest.getDirector();

        Movies movie = new Movies();
        movie.setId(movieId);
        movie.setTitle(title);
        movie.setDescription(description);
        movie.setReleaseDate(releaseDate);
        movie.setPosterUrl(posterUrl);
        movie.setGenres(genres);
        movie.setCast(cast);
        movie.setDirector(director);

        moviesDAO.saveMovie(movie);
        MoviesModel moviesModel = new ModelConverter().toMoviesModel(movie);

        return CreateMovieResult.builder()
            .withMoviesModel(moviesModel)
            .build();

    }

    private String createMovieId() {
        return UUID.randomUUID().toString().substring(0, 5);
    }
}
