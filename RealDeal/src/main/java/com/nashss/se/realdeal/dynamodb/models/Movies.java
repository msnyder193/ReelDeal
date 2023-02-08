package com.nashss.se.realdeal.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.nashss.se.realdeal.converter.ListConverter;

import java.util.List;
import java.util.Objects;

@DynamoDBTable(tableName = "Movies")
public class Movies {
    private String id;
    private String title;
    private String description;
    private String releaseDate;
    private String posterUrl;
    private List<String> genres;
    private List<String> cast;
    private String director;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @DynamoDBAttribute(attributeName = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @DynamoDBAttribute(attributeName = "releaseDate")
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    @DynamoDBAttribute(attributeName = "posterUrl")
    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
    @DynamoDBAttribute(attributeName = "genres")
    @DynamoDBTypeConverted(converter = ListConverter.class)
    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
    @DynamoDBAttribute(attributeName = "cast")
    @DynamoDBTypeConverted(converter = ListConverter.class)
    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }
    @DynamoDBAttribute(attributeName = "director")
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movies movies = (Movies) o;
        return id == movies.id && Objects.equals(title, movies.title) && Objects.equals(description, movies.description) && Objects.equals(releaseDate, movies.releaseDate) && Objects.equals(posterUrl, movies.posterUrl) && Objects.equals(genres, movies.genres) && Objects.equals(cast, movies.cast) && Objects.equals(director, movies.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, releaseDate, posterUrl, genres, cast, director);
    }

    @Override
    public String toString() {
        return "Movies{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", genres=" + genres +
                ", cast=" + cast +
                ", director='" + director + '\'' +
                '}';
    }
}
