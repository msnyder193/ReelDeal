package com.nashss.se.realdeal.activity.requests;

public class GetReviewRequest {
    private final String id;

    public GetReviewRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "GetReviewRequest{" +
            "id='" + id + '\'' +
            '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        public String id;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }
        public GetReviewRequest build() {
            return new GetReviewRequest(id);
        }
    }
}
