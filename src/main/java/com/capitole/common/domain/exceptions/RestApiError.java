package com.capitole.common.domain.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import lombok.Data;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RestApiError implements Serializable {
  @Serial private static final long serialVersionUID = -2278843985681971729L;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
  private Instant timestamp;

  @JsonIgnore private transient HttpStatus status;
  @JsonIgnore private transient RestApiErrorCode code;
  private String message;

  public RestApiError(RestApiErrorCode code) {
    this(null, code);
  }

  public RestApiError(HttpStatus status, RestApiErrorCode code) {
    this.timestamp = Instant.now();
    this.status = status;
    this.code = code;
    this.message = code.getMessage();
  }
}
