package com.nashss.se.realdeal.dynamodb;

import com.nashss.se.realdeal.dynamodb.models.Reviews;

public class ReviewDAO {
    private final DynamoDBMapper mapper;

    @Inject
    public ReviewDAO(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public Reviews getSingleReview(String id) {
        return mapper.load(Reviews.class, id);
    }
}
