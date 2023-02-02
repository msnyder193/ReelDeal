package com.nashss.se.realdeal.models;

import java.util.Objects;

public class ReviewsModel {
    private final int id;
    private final int movieId;
    private final String username;
    private final String text;
    private final int rating;

    /**
     * Creates a new reviews model
     * @param id the id of the
     * @param movieId the id of the
     * @param username the username
     * @param text the text
     * @param rating the rating
     */

    public ReviewsModel(int id, int movieId, String username, String text, int rating) {
        this.id = id;
        this.movieId = movieId;
        this.username = username;
        this.text = text;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public int getMovieId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewsModel that = (ReviewsModel) o;
        return id == that.id && movieId == that.movieId && rating == that.rating && Objects.equals(username, that.username) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movieId, username, text, rating);
    }

    public static ReviewsModel.Builder builder() {
        return new ReviewsModel.Builder();
    }

    public static class Builder {
        private int id;
        private int movieId;
        private String username;
        private String text;
        private int rating;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withMovieId(int movieId) {
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

        public ReviewsModel build() {
            return new ReviewsModel(id, movieId, username, text, rating);
        }
    }
}
