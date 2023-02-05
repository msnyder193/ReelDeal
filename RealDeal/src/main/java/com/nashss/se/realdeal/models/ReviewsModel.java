package com.nashss.se.realdeal.models;

import java.time.LocalDate;
import java.util.Objects;

public class ReviewsModel {
    private final int id;
    private final int movieId;
    private final String username;
    private final String text;
    private final int rating;
    private final LocalDate movieDate;

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

    public ReviewsModel(int id, int movieId, String username, String text, int rating, LocalDate movieDate) {
        this.id = id;
        this.movieId = movieId;
        this.username = username;
        this.text = text;
        this.rating = rating;
        this.movieDate = movieDate;
    }
    public LocalDate getMovieDate() {
        return movieDate;
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
        private int id;
        private int movieId;
        private String username;
        private String text;
        private int rating;
        private LocalDate movieDate;

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

        public Builder withMovieDate(LocalDate movieDate) {
            this.movieDate = movieDate;
            return this;
        }
        public ReviewsModel build() {
            return new ReviewsModel(id, movieId, username, text, rating, movieDate);
        }
    }
}
