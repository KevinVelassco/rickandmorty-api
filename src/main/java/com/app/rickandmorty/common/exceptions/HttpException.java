package com.app.rickandmorty.common.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class HttpException extends  RuntimeException {
    private final HttpStatus status;
    private final String message;
}