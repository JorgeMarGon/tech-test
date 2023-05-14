package com.capitole.product.price.domain.errors;

import com.capitole.common.domain.exceptions.RestApiErrorCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ProductPriceErrorCode implements RestApiErrorCode {
  PRICE_NOT_FOUND(1, "Price not found for that product"),
  PRICE_INVALID(2, "Invalid price for that product"),
  PRICE_PRIORITY_DUPLICATE(3, "Same priority for different available prices");

  private final int value;
  private final String message;

  public List<RestApiErrorCode> allValues() {
    return Arrays.stream(values()).map(RestApiErrorCode.class::cast).toList();
  }
}
