package ee.tenman.exam.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    protected ResponseEntity<ErrorResponse> handleGeneralException(GeneralException ex) {
        return getErrorResponseResponseEntity(ex);
    }

    @ExceptionHandler(ConversionFailedException.class)
    protected ResponseEntity<ErrorResponse> handleConflict(RuntimeException ex) {
        return getErrorResponseResponseEntity(ex);
    }

    private ResponseEntity<ErrorResponse> getErrorResponseResponseEntity(RuntimeException ex) {
        log.debug("Exception - {} and {}", ex.getClass(), ex.getMessage());
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .title(ErrorText.ERROR.getDescription())
                .message(ex.getMessage())
                .build();
        log.error("ERROR - {}, {}{}", ex.getMessage(), ex.getStackTrace());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errors = new StringBuilder();

        ex.getBindingResult().getAllErrors().forEach(error -> errors.append(((FieldError) error).getField())
                .append(" ")
                .append(error.getDefaultMessage()).append("\n"));

        final ErrorResponse errorResponse = ErrorResponse.builder()
                .title(ErrorText.VALIDATION_FAILED.getDescription())
                .message(errors.toString())
                .build();
        log.error("ERROR - {}, {}", ex.getMessage(), errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    protected ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException ex) {
        log.debug("Exception - {} and {}", ex.getClass(), ex.getMessage());
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .title(ErrorText.ERROR.getDescription())
                .message(ex.getMessage())
                .build();
        log.error("ERROR - {}, {}", ex.getMessage(), ex.getStackTrace());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
