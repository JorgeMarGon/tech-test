package com.capitole.common.domain.errors;

import com.capitole.common.domain.exceptions.RestApiErrorCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum GeneralErrorCode implements RestApiErrorCode {
  EXCEPTION_NOT_CONTROLLED(1, "Exception not controlled");

  private final int value;
  private final String message;

  @Override
  public String getName() {
    return this.name();
  }
}
