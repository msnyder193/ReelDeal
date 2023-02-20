package com.nashss.se.realdeal.activity;

import javax.inject.Inject;
import javax.management.InvalidAttributeValueException;

import com.nashss.se.realdeal.activity.requests.UpdateReviewRequest;
import com.nashss.se.realdeal.activity.results.UpdateReviewResult;
import com.nashss.se.realdeal.converter.ModelConverter;
import com.nashss.se.realdeal.dynamodb.DAO.ReviewDAO;

import com.nashss.se.realdeal.dynamodb.models.Reviews;
import com.nashss.se.realdeal.metrics.MetricsPublisher;
import com.nashss.se.realdeal.utils.RealDealServiceLambda;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateReviewActivity {
    private final Logger log = LogManager.getLogger();
    private final ReviewDAO reviewsDAO;
    private final MetricsPublisher metricsPublisher;

    @Inject
    public UpdateReviewActivity(ReviewDAO reviewsDAO, MetricsPublisher metricsPublisher) {
        this.reviewsDAO = reviewsDAO;
        this.metricsPublisher = metricsPublisher;
    }

    public UpdateReviewResult handleRequest(final UpdateReviewRequest request) {
        log.info("Received updateReviewRequest:" + request);

        if (!RealDealServiceLambda.isValidString(request.getId())) {
            try {
                throw new InvalidAttributeValueException("ReviewId" + request.getId() +
                    "contains an illegal character");
            } catch (InvalidAttributeValueException e) {
                throw new RuntimeException(e);
            }
        }

        Reviews review = reviewsDAO.getSingleReview(request.getId());

        if (!review.getId().equals(request.getId())) {
            try {
                throw new InvalidAttributeValueException("Can not change Review ID"
                    + review.getId());
            } catch (InvalidAttributeValueException e) {
                throw new RuntimeException(e);
            }
        }

        review.setText(request.getText());
        review.setMovieId(request.getId());
        review.setRating(request.getRating());

        return UpdateReviewResult.builder()
            .withReview(ModelConverter.toReviewsModel(review))
            .build();
    }
}