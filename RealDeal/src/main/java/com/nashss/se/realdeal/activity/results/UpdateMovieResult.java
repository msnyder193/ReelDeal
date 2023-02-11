package com.nashss.se.realdeal.activity.results;

import com.nashss.se.realdeal.models.MoviesModel;

public class UpdateMovieResult {
    private final MoviesModel movie;

    private UpdateMovieResult(MoviesModel movie) {
        this.movie = movie;
    }

    public MoviesModel getMovie() {
        return movie;
    }

    @Override
    public String toString() {
        return "UpdateMovieResult" +
            "movie: " + movie;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private MoviesModel movie;

        public Builder withMovie(MoviesModel movie) {
            this.movie = movie;
            return this;
        }

        public UpdateMovieResult build() {
            return new UpdateMovieResult(movie);
        }

    }
}