package com.nashss.se.realdeal.activity.results;

import com.nashss.se.realdeal.models.ReviewsModel;

public class DeleteReviewResult {
    private final ReviewsModel reviewsModel;

    public DeleteReviewResult(ReviewsModel reviewsModel) {
        this.reviewsModel = reviewsModel;
    }

    public ReviewsModel getReviewsModel() {
        return reviewsModel;
    }

    @Override
    public String toString() {
        return "DeleteReviewResult{" +
            "reviewsModel=" + reviewsModel +
            '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ReviewsModel reviewsModel;

        public Builder withReview(ReviewsModel reviewsModel) {
            this.reviewsModel = reviewsModel;
            return this;
        }

        public DeleteReviewResult build() {
            return new DeleteReviewResult(reviewsModel);
        }
    }
}
