package com.nashss.se.realdeal.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ListConverter implements DynamoDBTypeConverter<String, List>  {
    private static final Gson GSON = new Gson();
    private final Logger log = LogManager.getLogger();

    @Override
    public String convert(List listToBeConverted) {
        //TODO: update list conversion
        return null;
    }

    @Override
    public List unconvert(String dynamoDbRepresentation) {
        //TODO: update list unconverting
        return null;
    }
}
