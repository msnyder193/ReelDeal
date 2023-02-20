package com.nashss.se.realdeal.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

public class UpdateReviewRequest {
    private String id;

    private final String text;

    private final double rating;

    public UpdateReviewRequest(String text, double rating) {
        this.text = text;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }


    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "UpdateReviewRequest{" +
            "id='" + id + '\'' +
            ", text='" + text + '\'' +
            ", rating=" + rating +
            '}';
    }

    public static Builder builder() {
        return new Builder();
    }


    @JsonPOJOBuilder
    public static class Builder {
        private String id;

        private String text;

        private double rating;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withText(String text) {
            this.text = text;
            return this;
        }


        public Builder withRating(double rating) {
            this.rating = rating;
            return this;
        }

}

    public UpdateReviewRequest build() {
        return new UpdateReviewRequest(text,rating);
    }
}
