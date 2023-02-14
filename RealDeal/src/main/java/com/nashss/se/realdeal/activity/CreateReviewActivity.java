package com.nashss.se.realdeal.activity;

import javax.inject.Inject;

import java.time.LocalDate;
import java.util.UUID;

import com.nashss.se.realdeal.activity.requests.CreateReviewRequest;
import com.nashss.se.realdeal.activity.results.CreateReviewResult;
import com.nashss.se.realdeal.converter.ModelConverter;
import com.nashss.se.realdeal.dynamodb.DAO.ReviewDAO;
import com.nashss.se.realdeal.dynamodb.models.Reviews;
import com.nashss.se.realdeal.models.ReviewsModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateReviewActivity {
    private final Logger log = LogManager.getLogger();
    private final ReviewDAO reviewDAO;

    @Inject
    public CreateReviewActivity(ReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    public CreateReviewResult handleRequest(final CreateReviewRequest createReviewRequest) {
        log.info("Recieved CreateReviewRequest {}", createReviewRequest);
        String id = createReviewId();
        String movieId = createReviewRequest.getMovieId();
        String username = createReviewRequest.getUsername();
        String text = createReviewRequest.getText();
        int rating = createReviewRequest.getRating();
        String movieDate = createReviewRequest.getMovieDate();

        Reviews review = new Reviews();
        review.setId(id);
        review.setMovieId(movieId);
        review.setUsername(username);
        review.setText(text);
        review.setRating(rating);
        review.setMovieDate(movieDate);

        reviewDAO.saveReview(review);
        ReviewsModel reviewsModel = new ModelConverter().toReviewsModel(review);

        return CreateReviewResult.builder()
            .withReviewsModel(reviewsModel)
            .build();
    }

    private String createReviewId() {
        return UUID.randomUUID().toString().substring(0, 5);
    }
}
