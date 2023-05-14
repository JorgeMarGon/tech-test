package com.capitole.product.price.domain.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.capitole.common.domain.exceptions.RestApiException;
import com.capitole.product.price.domain.errors.ProductPriceErrorCode;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.common.PodamCollection;

@ExtendWith(MockitoExtension.class)
class ProductPriceTest {

  private static ProductPrice productPrice;

  @PodamCollection(nbrElements = 2)
  private static List<ProductPrice> productPrices;

  @BeforeAll
  public static void setUp() {
    PodamFactory factory = new PodamFactoryImpl();
    productPrice = factory.manufacturePojo(ProductPrice.class);
    productPrices = factory.manufacturePojo(List.class, ProductPrice.class);
  }

  @Test
  @DisplayName(
      "GIVEN: ProductPrice "
          + "WHEN: filtered within the price range "
          + "THEN: returns a correct ProductPrice")
  void idealScenario() {

    productPrice.setPriority(1);
    productPrice.setSelectedDate(LocalDateTime.of(2020, 12, 16, 16, 30, 10));

    productPrices.get(0).setPriority(3);
    productPrices.get(0).setStartDate(LocalDateTime.of(2020, 12, 15, 16, 30, 10));
    productPrices.get(0).setEndDate(LocalDateTime.of(2020, 12, 17, 16, 30, 10));

    productPrices.get(1).setPriority(4);
    productPrices.get(1).setStartDate(LocalDateTime.of(2020, 12, 16, 16, 29, 10));
    productPrices.get(1).setEndDate(LocalDateTime.of(2020, 12, 16, 16, 31, 10));

    assertEquals(productPrices.get(1), productPrice.findPriorityPrice(productPrices));
  }

  @Test
  @DisplayName(
      "GIVEN: ProductPrice "
          + "WHEN: filtered into a price range that has the same priority "
          + "THEN: returns an exception")
  void productPricesSamePriority() {

    productPrice.setPriority(1);
    productPrice.setSelectedDate(LocalDateTime.of(2020, 12, 16, 16, 30, 10));

    productPrices.get(0).setPriority(4);
    productPrices.get(0).setStartDate(LocalDateTime.of(2020, 12, 15, 16, 30, 10));
    productPrices.get(0).setEndDate(LocalDateTime.of(2020, 12, 17, 16, 30, 10));

    productPrices.get(1).setPriority(4);
    productPrices.get(1).setStartDate(LocalDateTime.of(2020, 12, 16, 16, 29, 10));
    productPrices.get(1).setEndDate(LocalDateTime.of(2020, 12, 16, 16, 31, 10));

    RestApiException ex =
        Assertions.assertThrows(
            RestApiException.class, () -> productPrice.findPriorityPrice(productPrices));
    Assertions.assertEquals(
        ex.getError().getCode().getValue(),
        ProductPriceErrorCode.PRICE_PRIORITY_DUPLICATE.getValue());
  }

  @Test
  @DisplayName(
      "GIVEN: ProductPrice "
          + "WHEN: filtering prices that are not in any date range "
          + "THEN: returns an exception")
  void productPricesOutOfRange() {

    productPrice.setPriority(1);
    productPrice.setSelectedDate(LocalDateTime.of(2024, 12, 16, 16, 30, 10));

    productPrices.get(0).setPriority(3);
    productPrices.get(0).setStartDate(LocalDateTime.of(2020, 12, 15, 16, 30, 10));
    productPrices.get(0).setEndDate(LocalDateTime.of(2020, 12, 17, 16, 30, 10));

    productPrices.get(1).setPriority(4);
    productPrices.get(1).setStartDate(LocalDateTime.of(2020, 12, 16, 16, 29, 10));
    productPrices.get(1).setEndDate(LocalDateTime.of(2020, 12, 16, 16, 31, 10));

    RestApiException ex =
        Assertions.assertThrows(
            RestApiException.class, () -> productPrice.findPriorityPrice(productPrices));
    Assertions.assertEquals(
        ex.getError().getCode().getValue(), ProductPriceErrorCode.PRICE_INVALID.getValue());
  }
}
