package com.nashss.se.realdeal.activity.results;

import com.nashss.se.realdeal.dynamodb.models.Movies;
import com.nashss.se.realdeal.models.MoviesModel;

public class GetMovieResult {
    private final MoviesModel singleMovie;

    public GetMovieResult(Movies singleMovie) {
        this.singleMovie = new MoviesModel(singleMovie.getId(), singleMovie.getTitle(), singleMovie.getDescription(),
            singleMovie.getReleaseDate(), singleMovie.getPosterUrl(), singleMovie.getGenres(), singleMovie.getCast(), singleMovie.getDirector());
    }

    public MoviesModel getSingleMovie() {
        return singleMovie;
    }

    @Override
    public String toString() {
        return "GetMovieResult{" +
                "singleMovie=" + singleMovie +
                '}';
    }

    public static GetMovieResult.Builder builder() {
        return new GetMovieResult.Builder();
    }

    public static class Builder {
        private Movies singleMovie;

        public GetMovieResult.Builder withId(Movies singleMovie) {
            this.singleMovie = singleMovie;
            return this;
        }

        public GetMovieResult build() {
            return new GetMovieResult(singleMovie);
        }

    }
}