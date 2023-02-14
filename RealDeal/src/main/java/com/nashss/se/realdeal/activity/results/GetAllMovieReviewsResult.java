package com.nashss.se.realdeal.activity.results;

import java.util.List;

import com.nashss.se.realdeal.models.ReviewsModel;

public class GetAllMovieReviewsResult {
    private final List<ReviewsModel> reviewsModelList;

    private GetAllMovieReviewsResult(List<ReviewsModel> reviewsModelList) {
        this.reviewsModelList = reviewsModelList;
    }

    public List<ReviewsModel> getReviewsModelList() {
        return reviewsModelList;
    }


    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<ReviewsModel> reviewsModelList;

        public Builder withReviews(List<ReviewsModel> reviewsModelList) {
            this.reviewsModelList = reviewsModelList;
            return this;
        }

        public GetAllMovieReviewsResult build() {
            return new GetAllMovieReviewsResult(reviewsModelList);
        }
    }
}
