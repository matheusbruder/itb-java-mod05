package com.meli.obterdiploma.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ExceptionDetails {
    private String title;
    private String message;
    private String fields;
    private String filedsMessage;
    private LocalDateTime timeStamp;
}
