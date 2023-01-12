package com.code.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

public class GlobalExceptionHandler {
    private final Logger logger = LogManager.getLogger();

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<ApiCallError<String>> handleNotFoundException(
            HttpServletRequest request, ChangeSetPersister.NotFoundException ex) {
        logger.error("NotFoundException {}\n", request.getRequestURI(), ex);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiCallError<>("Not found exception", List.of(ex.getMessage())));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiCallError<String>> handleValidationException(
            HttpServletRequest request, ValidationException ex) {
        logger.error("ValidationException {}\n", request.getRequestURI(), ex);

        return ResponseEntity.badRequest()
                .body(new ApiCallError<>("Validation exception", List.of(ex.getMessage())));
    }

//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<ApiCallError<String>> handleAccessDeniedException(
//            HttpServletRequest request, AccessDeniedException ex) {
//        logger.error("handleAccessDeniedException {}\n", request.getRequestURI(), ex);
//
//        return ResponseEntity.status(HttpStatus.FORBIDDEN)
//                .body(new ApiCallError<>("Access denied!", List.of(ex.getMessage())));
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiCallError<String>> handleInternalServerError(
            HttpServletRequest request, Exception ex) {
        logger.error("handleInternalServerError {}\n", request.getRequestURI(), ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiCallError<>("Internal server error", List.of(ex.getMessage())));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ApiCallError<T> {

        private String message;
        private List<T> details;
    }
}
