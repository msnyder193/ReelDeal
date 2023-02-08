package com.nashss.se.realdeal.dynamodb.DAO;

import javax.inject.Inject;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.realdeal.dynamodb.models.Users;

public class UsersDAO {
    private final DynamoDBMapper mapper;

    @Inject
    public UsersDAO(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public Users getUser(String username) {
        return mapper.load(Users.class, username);
    }
}
