package com.nashss.se.realdeal.activity.results;

import com.nashss.se.realdeal.models.ReviewsModel;

public class UpdateReviewResult {
    private final ReviewsModel review;

    private UpdateReviewResult(ReviewsModel review) {
        this.review = review;
    }

    public ReviewsModel getReview() {
        return review;
    }

    @Override
    public String toString() {
        return "UpdateReviewResult{" +
            "review=" + review +
            '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private ReviewsModel review;

        public Builder withReview(ReviewsModel review) {
            this.review = review;
            return this;
        }

        public UpdateReviewResult build() {
            return new UpdateReviewResult(review);
        }
    }
}
