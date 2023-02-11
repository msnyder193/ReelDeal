package com.nashss.se.realdeal.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GetAllMovieReviewsRequest.Builder.class)
public class GetAllMovieReviewsRequest {
    private final String movieId;

    private GetAllMovieReviewsRequest(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieId() {
        return movieId;
    }

    /**
     * Convert the GetMovieReviewsRequest to a string.
     * @return returns the GetAllMovieReviewsRequest as a string
     */

    public static Builder builder() {
        return new Builder();
    }

    //CHECKSTYLE:OFF:Builder
    @JsonPOJOBuilder
    public static class Builder {
        private String movieId;

        public Builder withMovieId(String movieId) {
            this.movieId = movieId;
            return this;
        }

        public GetAllMovieReviewsRequest build() {
            return new GetAllMovieReviewsRequest(movieId);
        }

        @Override
        public String toString() {
            return "Builder{" +
                "movieId='" + movieId + '\'' +
                '}';
        }
    }
}
