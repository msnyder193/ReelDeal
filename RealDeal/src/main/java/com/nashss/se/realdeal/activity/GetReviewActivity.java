package com.nashss.se.realdeal.activity;

import com.nashss.se.realdeal.activity.requests.GetReviewRequest;
import com.nashss.se.realdeal.activity.results.GetReviewResult;
import com.nashss.se.realdeal.dynamodb.DAO.ReviewDAO;
import com.nashss.se.realdeal.dynamodb.models.Reviews;
import com.nashss.se.realdeal.exception.ReviewNotFoundException;

import javax.inject.Inject;

public class GetReviewActivity {
    private final ReviewDAO reviewDAO;

    @Inject
    public GetReviewActivity(ReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    public GetReviewResult handleRequest(final GetReviewRequest getReviewRequest) {
        String requestedId = getReviewRequest.getId();

        Reviews singleReview = reviewDAO.getSingleReview(requestedId);

        if(singleReview == null) {
            throw new ReviewNotFoundException("Review not found with id " + requestedId);
        }

        return GetReviewResult.builder()
                .withId(singleReview)
                .build();
    }
}
