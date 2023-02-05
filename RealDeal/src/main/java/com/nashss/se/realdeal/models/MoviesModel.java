package com.nashss.se.realdeal.models;

import java.util.List;
import java.util.Objects;

public class MoviesModel {
    private final int id;
    private final String title;
    private final String description;
    private  final String releaseDate;
    private final String posterUrl;
    private final List<String> genres;
    private final List<String> cast;
    private final String director;

    /**
     * Creates a new MoviesModel
     * @param id
     * @param title
     * @param description
     * @param releaseDate
     * @param posterUrl
     * @param genres
     * @param cast
     * @param director
     */
    public MoviesModel(int id, String title, String description, String releaseDate, String posterUrl,
                       List<String> genres, List<String> cast, String director) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.posterUrl = posterUrl;
        this.genres = genres;
        this.cast = cast;
        this.director = director;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public List<String> getGenres() {
        return genres;
    }

    public List<String> getCast() {
        return cast;
    }

    public String getDirector() {
        return director;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoviesModel that = (MoviesModel) o;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(releaseDate, that.releaseDate) && Objects.equals(posterUrl, that.posterUrl) && Objects.equals(genres, that.genres) && Objects.equals(cast, that.cast) && Objects.equals(director, that.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, releaseDate, posterUrl, genres, cast, director);
    }

    public static MoviesModel.Builder builder() {
        return new MoviesModel.Builder();
    }

    public static class Builder {
        private int id;
        private String title;
        private String description;
        private String releaseDate;
        private String posterUrl;
        private List<String> genres;
        private List<String> cast;
        private String director;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public Builder withPosterUrl(String posterUrl) {
            this.posterUrl = posterUrl;
            return this;
        }

        public Builder withGenres(List<String> genres) {
            this.genres = genres;
            return this;
        }

        public Builder withCast(List<String> cast) {
            this.cast = cast;
            return this;
        }

        public Builder withDirector(String director) {
            this.director = director;
            return this;
        }

        public MoviesModel build() {
            return new MoviesModel(id, title, description, releaseDate, posterUrl, genres, cast, director);
        }
    }
}
