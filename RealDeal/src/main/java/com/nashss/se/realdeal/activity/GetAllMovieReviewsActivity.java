package com.nashss.se.realdeal.activity;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import com.nashss.se.realdeal.activity.requests.GetAllMovieReviewsRequest;
import com.nashss.se.realdeal.activity.results.GetAllMovieReviewsResult;
import com.nashss.se.realdeal.converter.ModelConverter;
import com.nashss.se.realdeal.dynamodb.DAO.ReviewDAO;
import com.nashss.se.realdeal.dynamodb.models.Reviews;
import com.nashss.se.realdeal.models.ReviewsModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetAllMovieReviewsActivity {
    private final Logger log = LogManager.getLogger();
    private final ReviewDAO reviewDAO;

    @Inject
    public GetAllMovieReviewsActivity(ReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    public GetAllMovieReviewsResult handleRequest(final GetAllMovieReviewsRequest request) {
        log.info("Recieved GetAllMovieReviewsRequest {}", request);
        List<Reviews> reviewsList = reviewDAO.getAllReviewsForMovie(request.getMovieId());
        List<ReviewsModel> reviewsModelList = new ArrayList<>();
        for (Reviews review : reviewsList) {
            ReviewsModel reviewModel = new ModelConverter().toReviewsModel(review);
            reviewsModelList.add(reviewModel);
        }
        return GetAllMovieReviewsResult.builder()
            .withReviews(reviewsModelList)
            .build();
    }
}
