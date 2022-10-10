package br.com.mpbruder.teste02.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ExceptionDetails {
    private String title;
    private String message;
    private String fields;
    private String filedsMessage;
    private LocalDateTime timeStamp;
}
