package com.bilgeadam.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorMessage {
    private int code;
    private String message;
    private List<String> fields;
    @Builder.Default
    private LocalDateTime date=LocalDateTime.now();

}
