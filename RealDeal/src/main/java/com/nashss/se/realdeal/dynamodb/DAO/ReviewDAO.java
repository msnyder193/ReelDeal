package com.nashss.se.realdeal.dynamodb.DAO;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.realdeal.dynamodb.models.Reviews;
import com.nashss.se.realdeal.metrics.MetricsPublisher;

import javax.inject.Inject;

public class ReviewDAO {
    private final DynamoDBMapper mapper;

    private final MetricsPublisher metricsPublisher;

    @Inject
    public ReviewDAO(DynamoDBMapper mapper, MetricsPublisher metricsPublisher) {
        this.mapper = mapper;
        this.metricsPublisher = metricsPublisher;
    }

    public Reviews getSingleReview(String id) {
        return mapper.load(Reviews.class, id);
    }
}
