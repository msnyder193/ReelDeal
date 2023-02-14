package com.nashss.se.realdeal.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.nashss.se.realdeal.models.ReviewsModel;

@JsonDeserialize(builder = CreateReviewRequest.Builder.class)
public class CreateReviewRequest {
    private final String id;
    private final String movieId;
    private final String username;
    private final String text;
    private final int rating;
    private final String movieDate;

    public CreateReviewRequest(String id, String movieId, String username,
                               String text, int rating, String movieDate) {
        this.id = id;
        this.movieId = movieId;
        this.username = username;
        this.text = text;
        this.rating = rating;
        this.movieDate = movieDate;
    }

    public String getId() {
        return id;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getUsername() {
        return username;
    }

    public String getText() {
        return text;
    }

    public int getRating() {
        return rating;
    }

    public String getMovieDate() {
        return movieDate;
    }

    @Override
    public String toString() {
        return "CreateReviewRequest{" +
            "id='" + id + '\'' +
            ", movieId='" + movieId + '\'' +
            ", username='" + username + '\'' +
            ", text='" + text + '\'' +
            ", rating=" + rating +
            ", movieDate=" + movieDate +
            '}';
    }

    public static Builder builder() {
        return new Builder();
    }
    @JsonPOJOBuilder
    public static class Builder {
        private String id;
        private String movieId;
        private String username;
        private String text;
        private int rating;
        private String movieDate;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withMovieId(String movieId) {
            this.movieId = movieId;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withText(String text) {
            this.text = text;
            return this;
        }

        public Builder withRating(int rating) {
            this.rating = rating;
            return this;
        }

        public Builder withMovieDate(String movieDate) {
            this.movieDate = movieDate;
            return this;
        }
        public CreateReviewRequest build() {
            return new CreateReviewRequest(id, movieId, username, text, rating, movieDate);
        }
    }
}
