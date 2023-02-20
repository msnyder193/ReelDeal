package com.nashss.se.realdeal.activity.requests;

public class DeleteReviewRequest {
    private String id;

    private String username;

    private DeleteReviewRequest(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "DeleteReviewRequest{" +
            "id='" + id + '\'' +
            ", username='" + username + '\'' +
            '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String username;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public DeleteReviewRequest build() {
            return new DeleteReviewRequest(id, username);
        }
    }
}
