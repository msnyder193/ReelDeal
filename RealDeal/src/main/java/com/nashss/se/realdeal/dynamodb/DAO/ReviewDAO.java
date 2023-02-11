package com.nashss.se.realdeal.dynamodb.DAO;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.nashss.se.realdeal.dynamodb.models.Reviews;
import com.nashss.se.realdeal.exception.ReviewNotFoundException;
import com.nashss.se.realdeal.metrics.MetricsConstants;
import com.nashss.se.realdeal.metrics.MetricsPublisher;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReviewDAO {
    private final DynamoDBMapper mapper;

    private final MetricsPublisher metricsPublisher;

    @Inject
    public ReviewDAO(DynamoDBMapper mapper, MetricsPublisher metricsPublisher) {
        this.mapper = mapper;
        this.metricsPublisher = metricsPublisher;
    }

    public Reviews getSingleReview(String id) {
        Reviews reviews = this.mapper.load(Reviews.class, id);
        if (reviews == null) {
            metricsPublisher.addCount(MetricsConstants.GETREVIEW_REVIEWNOTFOUND_COUNT, 1);
            throw new ReviewNotFoundException("Could not find reviews with this id");
        }
        metricsPublisher.addCount(MetricsConstants.GETREVIEW_REVIEWNOTFOUND_COUNT, 0);
        return reviews;
    }

    public void saveReview(Reviews review) {
        this.mapper.save(review);
    } 
    
    public List<Reviews> getAllReviewsForMovie(String movieId) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":movieId", new AttributeValue().withS(movieId));

        DynamoDBQueryExpression<Reviews> queryExpression = new DynamoDBQueryExpression<Reviews>()
            .withIndexName("ReviewByMovieIdIndex")
            .withConsistentRead(false)
            .withKeyConditionExpression("movieId = :movieId")
            .withExpressionAttributeValues(valueMap);

        PaginatedQueryList<Reviews> reviewsList = mapper.query(Reviews.class, queryExpression);
        return reviewsList;
    }
}
