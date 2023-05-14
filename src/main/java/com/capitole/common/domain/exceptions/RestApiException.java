package com.capitole.common.domain.exceptions;

import java.io.Serial;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class RestApiException extends RuntimeException {

  @Serial private static final long serialVersionUID = -1335991811592142594L;
  protected final RestApiError error;

  public RestApiException(RestApiErrorCode code, HttpStatus status) {
    super(String.format("Error: %s", code.getValue()));
    error = new RestApiError(status, code);
  }
}
