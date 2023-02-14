package com.nashss.se.realdeal.models;

import java.util.Objects;

public class ReviewsModel {
    private final String id;
    private final String movieId;
    private final String username;
    private final String text;
    private final double rating;
    private final String movieDate;

    /**
     * Creates a new reviews model
     *
     * @param id        the id of the
     * @param movieId   the id of the
     * @param username  the username
     * @param text      the text
     * @param rating    the rating
     * @param movieDate the date
     */

    public ReviewsModel(String id, String movieId, String username, String text, double rating, String movieDate) {
        this.id = id;
        this.movieId = movieId;
        this.username = username;
        this.text = text;
        this.rating = rating;
        this.movieDate = movieDate;
    }
    public String getMovieDate() {
        return movieDate;
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

    public double getRating() {
        return rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewsModel that = (ReviewsModel) o;
        return id == that.id && movieId == that.movieId && rating == that.rating && Objects.equals(username, that.username) && Objects.equals(text, that.text) && Objects.equals(movieDate, that.movieDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movieId, username, text, rating, movieDate);
    }

    public static ReviewsModel.Builder builder() {
        return new ReviewsModel.Builder();
    }

    public static class Builder {
        private String id;
        private String movieId;
        private String username;
        private String text;
        private double rating;
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

        public Builder withRating(double rating) {
            this.rating = rating;
            return this;
        }

        public Builder withMovieDate(String movieDate) {
            this.movieDate = movieDate;
            return this;
        }
        public ReviewsModel build() {
            return new ReviewsModel(id, movieId, username, text, rating, movieDate);
        }
    }
}
