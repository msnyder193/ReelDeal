package com.nashss.se.realdeal.activity.requests;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.nashss.se.realdeal.models.MoviesModel;

@JsonDeserialize(builder = CreateMovieRequest.Builder.class)
public class CreateMovieRequest {
    private final String id;
    private final String title;
    private final String description;
    private final String releaseDate;
    private final String posterUrl;
    private final Set<String> genres;
    private final Set<String> cast;
    private final String director;

    public CreateMovieRequest(String id, String title, String description, String releaseDate, String posterUrl,
                              Set<String> genres, Set<String> cast, String director) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.posterUrl = posterUrl;
        this.genres = genres;
        this.cast = cast;
        this.director = director;
    }

    public String getId() {
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

    public Set<String> getGenres() {
        return genres;
    }

    public Set<String> getCast() {
        return cast;
    }

    public String getDirector() {
        return director;
    }

    @Override
    public String toString() {
        return "CreateMovieRequest{" +
            "id='" + id + '\'' +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", releaseDate='" + releaseDate + '\'' +
            ", posterUrl='" + posterUrl + '\'' +
            ", genres=" + genres +
            ", cast=" + cast +
            ", director='" + director + '\'' +
            '}';
    }

    public static Builder builder() {
        return new Builder();
    }
    @JsonPOJOBuilder
    public static class Builder {
        private String id;
        private String title;
        private String description;
        private String releaseDate;
        private String posterUrl;
        private Set<String> genres;
        private Set<String> cast;
        private String director;

        public Builder withId(String id) {
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

        public Builder withGenres(Set<String> genres) {
            this.genres = genres;
            return this;
        }

        public Builder withCast(Set<String> cast) {
            this.cast = cast;
            return this;
        }

        public Builder withDirector(String director) {
            this.director = director;
            return this;
        }

        public CreateMovieRequest build() {
            return new CreateMovieRequest(id, title, description, releaseDate, posterUrl, genres, cast, director);
        }
    }
}
