package com.nashss.se.realdeal.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter implements DynamoDBTypeConverter<String, LocalDate> {
    @Override
    public String convert(LocalDate obj) {
        return (obj == null) ? null : obj.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public LocalDate unconvert(String obj) {
        return (obj == null) ? null : LocalDate.parse(obj, DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
