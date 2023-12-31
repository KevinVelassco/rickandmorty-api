package com.app.rickandmorty.utils.handledatabaseexception;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record DatabaseExceptionResponse(HttpStatus statusCode, String message) {}


