package com.capitole.common.infrastructure.configuration.exceptions;

import com.capitole.common.domain.errors.GeneralErrorCode;
import com.capitole.common.domain.exceptions.RestApiError;
import com.capitole.common.domain.exceptions.RestApiException;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({RestApiException.class})
  public ResponseEntity<Object> handleAll(final RestApiException ex) {
    log.error("Rest Api Exception: {}", ex.getMessage());
    HttpStatus status = HttpStatus.resolve(ex.getError().getStatus().value());
    if (Objects.isNull(status)) status = HttpStatus.INTERNAL_SERVER_ERROR;
    return new ResponseEntity<>(ex.getError(), new HttpHeaders(), status);
  }

  @ExceptionHandler({Exception.class})
  public ResponseEntity<Object> handleAll(Exception ex) {
    log.error("EXCEPTION NOT CONTROLLED", ex);
    RestApiError rae = new RestApiError(GeneralErrorCode.EXCEPTION_NOT_CONTROLLED);
    return new ResponseEntity<>(rae, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
