package com.app.rickandmorty.utils.handledatabaseexception;

import org.springframework.http.HttpStatus;
import java.sql.SQLException;
import java.util.Objects;

public class HandleResponseFromDatabaseException {
    public static final String NOT_NULL_CONSTRAINT_ERROR_CODE = "23502";
    public static final String UNIQUE_CONSTRAINT_ERROR_CODE = "23505";
    public static final String VALUE_TOO_LONG_ERROR_CODE = "22001";

    public static DatabaseExceptionResponse response(SQLException sqlException) {
        if (Objects.equals(sqlException.getSQLState(), NOT_NULL_CONSTRAINT_ERROR_CODE)) {
            return DatabaseExceptionResponse.builder()
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .message("there are mandatory properties please check")
                    .build();
        }

        if (Objects.equals(sqlException.getSQLState(), UNIQUE_CONSTRAINT_ERROR_CODE)) {

            String errorMessage = sqlException.getMessage();

            String message = errorMessage.contains("Detail")
                    ? errorMessage.substring(errorMessage.indexOf("Detail"))
                    : "some property violates a unique constraint, please check";

            return DatabaseExceptionResponse.builder()
                    .statusCode(HttpStatus.CONFLICT)
                    .message(message)
                    .build();
        }

        if (Objects.equals(sqlException.getSQLState(), VALUE_TOO_LONG_ERROR_CODE)) {
            return DatabaseExceptionResponse.builder()
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .message("some property exceeds the allowed length")
                    .build();
        }

        return null;
    }
}
