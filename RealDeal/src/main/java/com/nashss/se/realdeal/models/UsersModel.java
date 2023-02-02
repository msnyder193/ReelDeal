package com.nashss.se.realdeal.models;

import com.nashss.se.realdeal.dynamodb.models.Reviews;

import java.util.List;
import java.util.Objects;

public class UsersModel {
    private final String username;

    private final String password;

    private final String email;

    private final List<Reviews> reviews;

    /**
     * users constructor
     * @param username
     * @param password
     * @param email
     * @param reviews
     */

    public UsersModel(String username, String password, String email, List<Reviews> reviews) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.reviews = reviews;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public List<Reviews> getReviews() {
        return reviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersModel that = (UsersModel) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(email, that.email) && Objects.equals(reviews, that.reviews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, reviews);
    }

    public static UsersModel.Builder builder() {
        return new UsersModel.Builder();
    }

    public static class Builder {

        private String username;
        private String password;
        private String email;
        private List<Reviews> reviews;

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withReviews(List<Reviews> reviews) {
            this.reviews = reviews;
            return this;
        }

        public UsersModel build() {
            return new UsersModel(username, password, email, reviews);
        }
    }
}
