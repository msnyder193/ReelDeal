package com.nashss.se.realdeal.activity;

import javax.inject.Inject;

import com.nashss.se.realdeal.activity.requests.DeleteReviewRequest;
import com.nashss.se.realdeal.activity.results.DeleteReviewResult;
import com.nashss.se.realdeal.converter.ModelConverter;
import com.nashss.se.realdeal.dynamodb.DAO.ReviewDAO;
import com.nashss.se.realdeal.dynamodb.models.Reviews;
import com.nashss.se.realdeal.exception.UserNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteReviewActivity {
    private final Logger log = LogManager.getLogger();

    private final ReviewDAO reviewDAO;

    @Inject
    public DeleteReviewActivity(ReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    public DeleteReviewResult handleRequest (final DeleteReviewRequest request) {
        log.info("Recieved deleteReviewRequest {}", request);

        if (request.getId() == null) {
            throw new UserNotFoundException();
        }

        if(reviewDAO.getSingleReview(request.getId()) == null) {
            throw new UserNotFoundException();
        }

        Reviews review = reviewDAO.getSingleReview(request.getId());

        if(!review.getUsername().equals(request.getUsername())) {
            throw new SecurityException("you must be the owner of this review to delete");
        }

        reviewDAO.deleteReview(review);

        return DeleteReviewResult.builder()
            .withReview(ModelConverter.toReviewsModel(review))
            .build();
    }
}
