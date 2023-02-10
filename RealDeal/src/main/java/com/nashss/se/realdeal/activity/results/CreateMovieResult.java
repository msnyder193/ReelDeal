package com.nashss.se.realdeal.activity.results;

import com.nashss.se.realdeal.models.MoviesModel;

public class CreateMovieResult {
    private final MoviesModel moviesModel;

    private CreateMovieResult(MoviesModel moviesModel) {
        this.moviesModel = moviesModel;
    }

    public MoviesModel getMoviesModel() {
        return moviesModel;
    }

    @Override
    public String toString() {
        return "CreateMovieResult{" +
            "moviesModel=" + moviesModel +
            '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private MoviesModel moviesModel;

        public Builder withMoviesModel(MoviesModel moviesModel) {
            this.moviesModel = moviesModel;
            return this;
        }

        public CreateMovieResult build() {
            return new CreateMovieResult(moviesModel);
        }
    }
}
