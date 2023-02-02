package com.nashss.se.realdeal.activity.results;

import com.nashss.se.realdeal.dynamodb.models.Movies;

public class GetMovieResult {
    private Movies singleMovie;

    public GetMovieResult(Movies singleMovie) {
        this.singleMovie = singleMovie;
    }

    public Movies getSingleReview() {
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