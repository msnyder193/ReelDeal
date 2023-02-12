package com.nashss.se.realdeal.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.nashss.se.realdeal.converter.LocalDateConverter;

import java.time.LocalDate;
import java.util.Objects;
@DynamoDBTable(tableName = "Reviews")
public class Reviews {
    private String id;
    private String movieId;
    private String username;
    private String text;
    private int rating;
    private String movieDate;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "movieId")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "ReviewByMovieIdIndex")
    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "ReviewByUsernameIndex")
    @DynamoDBAttribute(attributeName = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @DynamoDBAttribute(attributeName = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    @DynamoDBAttribute(attributeName = "rating")
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @DynamoDBAttribute(attributeName = "movieDate")
    @DynamoDBTypeConverted(converter = LocalDateConverter.class)
    public String getMovieDate() {
        return movieDate;
    }

    public void setMovieDate(String date) {
        this.movieDate = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reviews reviews = (Reviews) o;
        return id == reviews.id && movieId == reviews.movieId && rating == reviews.rating && Objects.equals(username, reviews.username) && Objects.equals(text, reviews.text) && Objects.equals(movieDate, reviews.movieDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movieId, username, text, rating, movieDate);
    }

    @Override
    public String toString() {
        return "Reviews{" +
                "id=" + id +
                ", movieId=" + movieId +
                ", username='" + username + '\'' +
                ", text='" + text + '\'' +
                ", rating=" + rating +
                ", date=" + movieDate +
                '}';
    }
}
