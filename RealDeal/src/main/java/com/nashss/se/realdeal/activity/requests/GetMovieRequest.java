package com.nashss.se.realdeal.activity.requests;

public class GetMovieRequest {
    private final String id;

    public GetMovieRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "GetMovieRequest{" +
                "id='" + id + '\'' +
                '}';
    }

    public static GetMovieRequest.Builder builder() {
        return new GetMovieRequest.Builder();
    }

    public static class Builder {
        public String id;
        public GetMovieRequest.Builder withId(String id) {
            this.id = id;
            return this;
        }
    }

    public GetMovieRequest build() {
        return new GetMovieRequest(id);
    }
}
