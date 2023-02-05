package com.nashss.se.realdeal.converter;

import com.nashss.se.realdeal.dynamodb.models.Movies;
import com.nashss.se.realdeal.dynamodb.models.Reviews;
import com.nashss.se.realdeal.dynamodb.models.Users;
import com.nashss.se.realdeal.models.MoviesModel;
import com.nashss.se.realdeal.models.ReviewsModel;
import com.nashss.se.realdeal.models.UsersModel;

public class ModelConverter {
    public MoviesModel toMoviesModel(Movies movies) {
        return MoviesModel.builder()
                .withCast(movies.getCast())
                .withDescription(movies.getDescription())
                .withDirector(movies.getDirector())
                .withGenres(movies.getGenres())
                .withId(movies.getId())
                .withPosterUrl(movies.getPosterUrl())
                .withReleaseDate(movies.getReleaseDate())
                .withTitle(movies.getTitle())
                .build();
    }

    public ReviewsModel toReviewsModel(Reviews reviews) {
        return ReviewsModel.builder()
                .withId(reviews.getId())
                .withMovieId(reviews.getMovieId())
                .withRating(reviews.getRating())
                .withText(reviews.getText())
                .withMovieDate(reviews.getMovieDate())
                .withUsername(reviews.getUsername())
                .build();
    }

    public UsersModel toUsersModel(Users users) {
        return UsersModel.builder()
                .withUsername(users.getUsername())
                .withPassword(users.getPassword())
                .withEmail(users.getEmail())
                .withReviews(users.getReviews())
                .build();
    }
}
