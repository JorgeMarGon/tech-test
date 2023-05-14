package com.capitole.product.price.domain.models;

import com.capitole.common.domain.exceptions.RestApiException;
import com.capitole.product.price.domain.errors.ProductPriceErrorCode;
import com.capitole.product.price.domain.types.CurrencyType;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ProductPrice {
  private Integer id;
  private Integer tariff;
  private Integer priority;
  private Double price;
  private CurrencyType currency;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private LocalDateTime selectedDate;
  private Brand brand;

  public ProductPrice findPriorityPrice(List<ProductPrice> productPrices) {
    return productPrices.stream()
        .filter(
            product ->
                selectedDate.isAfter(product.getStartDate())
                    && selectedDate.isBefore(product.getEndDate()))
        .max(Comparator.comparingInt(ProductPrice::getPriority))
        .filter(
            price ->
                productPrices.stream()
                        .filter(
                            product ->
                                selectedDate.isAfter(product.getStartDate())
                                    && selectedDate.isBefore(product.getEndDate()))
                        .filter(
                            product -> Objects.equals(product.getPriority(), price.getPriority()))
                        .count()
                    <= 1)
        .orElseThrow(
            () ->
                new RestApiException(
                    productPrices.stream()
                            .filter(
                                product ->
                                    selectedDate.isAfter(product.getStartDate())
                                        && selectedDate.isBefore(product.getEndDate()))
                            .allMatch(product -> product.getPriority() == 0)
                        ? ProductPriceErrorCode.PRICE_INVALID
                        : ProductPriceErrorCode.PRICE_PRIORITY_DUPLICATE,
                    HttpStatus.UNPROCESSABLE_ENTITY));
  }
}
