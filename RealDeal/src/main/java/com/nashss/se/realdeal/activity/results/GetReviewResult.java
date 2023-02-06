package com.nashss.se.realdeal.activity.results;

import com.nashss.se.realdeal.dynamodb.models.Reviews;

public class GetReviewResult {
    private final Reviews singleReview;

    public GetReviewResult(Reviews singleReview) {
        this.singleReview = singleReview;
    }

    public Reviews getSingleReview() {
        return singleReview;
    }

    @Override
    public String toString() {
        return "GetReviewResult{" +
                "singleReview=" + singleReview +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private Reviews singleReview;

        public Builder withId(Reviews singleReview) {
            this.singleReview = singleReview;
            return this;
        }

        public GetReviewResult build() {
            return new GetReviewResult(singleReview);
        }
    }
}
