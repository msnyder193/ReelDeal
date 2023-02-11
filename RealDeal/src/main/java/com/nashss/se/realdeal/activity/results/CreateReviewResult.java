package com.nashss.se.realdeal.activity.results;

import com.nashss.se.realdeal.models.ReviewsModel;

public class CreateReviewResult {
    private final ReviewsModel reviewsModel;

    public CreateReviewResult(ReviewsModel reviewsModel) {
        this.reviewsModel = reviewsModel;
    }

    public ReviewsModel getReviewsModel() {
        return reviewsModel;
    }

    @Override
    public String toString() {
        return "CreateReviewResult{" +
            "reviewsModel=" + reviewsModel +
            '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ReviewsModel    reviewsModel;

        public Builder withReviewsModel(ReviewsModel reviewsModel) {
            this.reviewsModel = reviewsModel;
            return this;
        }

        public CreateReviewResult build() {
            return new CreateReviewResult(reviewsModel);
        }
    }
}
