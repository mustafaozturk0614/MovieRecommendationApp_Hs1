package com.bilgeadam.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
public interface DateMapper {
    @Named("localDateToLong")
    default Long localDateToLong(LocalDate date) {
        return date == null ? null : date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    @Named("longToLocalDate")
    default LocalDate longToLocalDate(Long millis) {
        return millis == null ? null : Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
