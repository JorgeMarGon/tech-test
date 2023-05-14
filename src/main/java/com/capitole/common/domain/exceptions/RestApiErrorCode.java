package com.capitole.common.domain.exceptions;

import java.util.List;

public interface RestApiErrorCode {

  int getValue();

  List<RestApiErrorCode> allValues();

  String getMessage();

  default RestApiErrorCode valueOf(int codeValue) {
    return allValues().stream()
        .filter(code -> getValue() == codeValue)
        .findFirst()
        .orElseThrow(
            () -> new IllegalArgumentException("No matching constant for [" + codeValue + "]"));
  }
}
