package com.nashss.se.realdeal.activity.results;

import java.util.List;

import com.nashss.se.realdeal.models.MoviesModel;

public class GetAllMoviesResult {
    private final List<MoviesModel> moviesModelList;

    private GetAllMoviesResult(List<MoviesModel> moviesModelList) {
        this.moviesModelList = moviesModelList;
    }

    public List<MoviesModel> getMoviesModelList() {
        return moviesModelList;
    }


    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<MoviesModel> moviesModelList;

        public Builder withMovies(List<MoviesModel> moviesModelList) {
            this.moviesModelList = moviesModelList;
            return this;
        }

        public GetAllMoviesResult build() {
            return new GetAllMoviesResult(moviesModelList);
        }

    }
}