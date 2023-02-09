package com.nashss.se.realdeal.activity.requests;

public class GetAllMoviesRequest {
    private GetAllMoviesRequest() {

    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        public GetAllMoviesRequest build() {
            return new GetAllMoviesRequest();
        }
    }
}
