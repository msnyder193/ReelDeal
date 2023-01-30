package com.nashss.se.realdeal.dynamodb.models;

import com.nashss.se.realdeal.converter.LocalDateConverter;

import java.time.LocalDate;
import java.util.Objects;

public class Reviews {
    private int id;
    private int movieId;
    private String username;
    private String text;
    private int rating;
    private LocalDate movieDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @DynamoDBAttrribute(attributeName = "moviedate")
    @DynamoDBTypeConverted(converter = LocalDateConverter.class)
    public LocalDate getMovieDate() {
        return date;
    }

    public void setMovieDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reviews reviews = (Reviews) o;
        return id == reviews.id && movieId == reviews.movieId && rating == reviews.rating && Objects.equals(username, reviews.username) && Objects.equals(text, reviews.text) && Objects.equals(date, reviews.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movieId, username, text, rating, date);
    }

    @Override
    public String toString() {
        return "Reviews{" +
                "id=" + id +
                ", movieId=" + movieId +
                ", username='" + username + '\'' +
                ", text='" + text + '\'' +
                ", rating=" + rating +
                ", date=" + date +
                '}';
    }
}
