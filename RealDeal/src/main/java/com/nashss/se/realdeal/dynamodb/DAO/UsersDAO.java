package com.nashss.se.realdeal.dynamodb.DAO;

import javax.inject.Inject;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.realdeal.dynamodb.models.Users;
import com.nashss.se.realdeal.exception.UserNotFoundException;
import com.nashss.se.realdeal.metrics.MetricsConstants;
import com.nashss.se.realdeal.metrics.MetricsPublisher;

public class UsersDAO {
    private final DynamoDBMapper mapper;
    private final MetricsPublisher metricsPublisher;

    @Inject
    public UsersDAO(DynamoDBMapper mapper, MetricsPublisher metricsPublisher) {
        this.mapper = mapper;
        this.metricsPublisher = metricsPublisher;
    }

    public Users getUser(String username) {
        Users users = this.mapper.load(Users.class, username);
        if (users == null) {
            metricsPublisher.addCount(MetricsConstants.GETUSER_USERNOTFOUND_COUNT, 1);
            throw new UserNotFoundException("User not found with this username");
        }
        metricsPublisher.addCount(MetricsConstants.GETUSER_USERNOTFOUND_COUNT, 0);
        return users;
    }
}
