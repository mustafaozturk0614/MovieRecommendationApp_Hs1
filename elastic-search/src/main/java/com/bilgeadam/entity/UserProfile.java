package com.bilgeadam.entity;

import com.bilgeadam.entity.enums.EStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Document(indexName = "user-profile")
public class UserProfile extends BaseEntity{
    @Id
    private String id;
    private Long authId;
    private String username;
    private String email;
    private String name;
    private String surName;
    private String phone;
    private String address;
    private String avatar;
    private String about;
    private Long birthDate;
    @Builder.Default
    private EStatus status=EStatus.PENDING;
    private List<String> favMovies;
    private List<String> favGenres;
    private List<String> favComments;
    private Map<String,Double> ratings;


}
