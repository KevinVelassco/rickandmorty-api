package com.app.rickandmorty.config;


import com.app.rickandmorty.common.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@ControllerAdvice
public class ErrorHandlerConfig {

    private ApiErrorResponse apiErrorResponse (HttpStatus httpStatus, RuntimeException exception) {
        return ApiErrorResponse
            .builder()
            .statusCode(httpStatus.value())
            .error(httpStatus.getReasonPhrase())
            .message(exception.getMessage())
            .timestamp(LocalDateTime.now())
            .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleException(MethodArgumentNotValidException exception) {
        Map<String, List<String>> errors = new HashMap<>();

        exception.getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();

                    if (errors.get(fieldName) == null && errorMessage != null) {
                        errors.put(fieldName, new ArrayList<>(List.of(errorMessage)));
                    } else {
                        errors.get(fieldName).add(errorMessage);
                    }
                });

        ApiErrorResponse apiErrorResponse = ApiErrorResponse
                .builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(errors)
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(
                apiErrorResponse,
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiErrorResponse> handleException(BadRequestException exception,
                                                    WebRequest request) {

        log.error(exception.getMessage(), exception);

        return new ResponseEntity<>(
                apiErrorResponse(HttpStatus.BAD_REQUEST, exception),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiErrorResponse> handleException(UnauthorizedException exception,
                                                    WebRequest request) {

        log.error(exception.getMessage(), exception);

        return new ResponseEntity<>(
                apiErrorResponse(HttpStatus.UNAUTHORIZED, exception),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiErrorResponse> handleException(ForbiddenException exception,
                                                    WebRequest request) {

        log.error(exception.getMessage(), exception);

        return new ResponseEntity<>(
                apiErrorResponse(HttpStatus.FORBIDDEN, exception),
                HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleException(NotFoundException exception,
                                                    WebRequest request) {

        log.error(exception.getMessage(), exception);

        return new ResponseEntity<>(
                apiErrorResponse(HttpStatus.NOT_FOUND, exception),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<ApiErrorResponse> handleException(MethodNotAllowedException exception,
                                                    WebRequest request) {

        log.error(exception.getMessage(), exception);

        return new ResponseEntity<>(
                apiErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, exception),
                HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiErrorResponse> handleException(ConflictException exception,
                                                    WebRequest request) {

        log.error(exception.getMessage(), exception);

        return new ResponseEntity<>(
                apiErrorResponse(HttpStatus.CONFLICT, exception),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnsupportedMediaTypeException.class)
    public ResponseEntity<ApiErrorResponse> handleException(UnsupportedMediaTypeException exception,
                                                    WebRequest request) {

        log.error(exception.getMessage(), exception);

        return new ResponseEntity<>(
                apiErrorResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE, exception),
                HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ApiErrorResponse> handleException(InternalServerErrorException exception,
                                                    WebRequest request) {

        log.error(exception.getMessage(), exception);

        return new ResponseEntity<>(
                apiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadGatewayException.class)
    public ResponseEntity<ApiErrorResponse> handleException(BadGatewayException exception,
                                                    WebRequest request) {

        log.error(exception.getMessage(), exception);

        return new ResponseEntity<>(
                apiErrorResponse(HttpStatus.BAD_GATEWAY, exception),
                HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<ApiErrorResponse> handleException(ServiceUnavailableException exception,
                                                    WebRequest request) {

        log.error(exception.getMessage(), exception);

        return new ResponseEntity<>(
                apiErrorResponse(HttpStatus.SERVICE_UNAVAILABLE, exception),
                HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(GatewayTimeoutException.class)
    public ResponseEntity<ApiErrorResponse> handleException(GatewayTimeoutException exception,
                                                    WebRequest request) {

        log.error(exception.getMessage(), exception);

        return new ResponseEntity<>(
                apiErrorResponse(HttpStatus.GATEWAY_TIMEOUT, exception),
                HttpStatus.GATEWAY_TIMEOUT);
    }

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ApiErrorResponse> handleException(HttpException exception,
                                                            WebRequest request) {

        log.error(exception.getMessage(), exception);

        return new ResponseEntity<>(
                apiErrorResponse(exception.getStatus(), exception),
                exception.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception exception,
                                                    WebRequest request) {

        log.error(exception.getMessage(), exception);

        ApiErrorResponse apiErrorResponse = ApiErrorResponse
                .builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(
                apiErrorResponse,
                HttpStatus.INTERNAL_SERVER_ERROR);

    }
}