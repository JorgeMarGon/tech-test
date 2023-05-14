package com.capitole.product.price.infrastructure.adapters.get_product_price;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import com.capitole.common.domain.exceptions.RestApiException;
import com.capitole.product.price.domain.errors.ProductPriceErrorCode;
import com.capitole.product.price.domain.models.ProductPrice;
import com.capitole.product.price.infrastructure.mappers.ProductPriceMapper;
import com.capitole.product.price.infrastructure.persistence.dao.ProductPriceRepositoryJpa;
import com.capitole.product.price.infrastructure.persistence.jpa.ProductPriceJpa;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductPriceRepositoryAdapterTest {

  @Mock private ProductPriceRepositoryJpa productPriceRepositoryJpa;
  @Mock private ProductPriceMapper productPriceMapper;

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private ProductPriceJpa productPriceJpa;

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private ProductPrice productPrice;

  @InjectMocks private ProductPriceRepositoryAdapter mockAdapter;

  @Test
  @DisplayName("GIVEN: ProductPrice " + "WHEN: getting data match " + "THEN: returns that items")
  void idealScenario() {
    when(productPriceRepositoryJpa.findByProductIdAndBrand_Id(anyInt(), anyInt()))
        .thenReturn(List.of(productPriceJpa));

    assertDoesNotThrow(() -> mockAdapter.findAllByIdAndBrandId(productPrice));
    verify(productPriceMapper).jpaToDomain(productPriceJpa);
  }

  @Test
  @DisplayName(
      "GIVEN: ProductPrice "
          + "WHEN: getting data that has to match "
          + "THEN: returns an exception")
  void productPriceNotFound() {
    when(productPriceRepositoryJpa.findByProductIdAndBrand_Id(anyInt(), anyInt()))
        .thenReturn(List.of());

    RestApiException ex =
        assertThrows(RestApiException.class, () -> mockAdapter.findAllByIdAndBrandId(productPrice));

    verify(productPriceMapper, never()).jpaToDomain(any());
    assertEquals(
        ex.getError().getCode().getValue(), ProductPriceErrorCode.PRICE_NOT_FOUND.getValue());
  }
}
