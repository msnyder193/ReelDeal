package com.nashss.se.realdeal.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter implements DynamoDBTypeConverter<String, LocalDate> {
    @Override
    public String convert(LocalDate obj) {
        return (obj == null) ? null : obj.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public LocalDate unconvery(String obj) {
        return (obj == null) ? null : LocalDate.parse(obj, DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
