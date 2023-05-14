package com.capitole.product.price.infrastructure.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import com.capitole.product.price.application.common.ProductPriceEventHandler;
import com.capitole.product.price.application.get_product_price.GetProductPriceByBrandInDateQuery;
import com.capitole.product.price.domain.models.ProductPrice;
import com.capitole.product.price.infrastructure.mappers.ProductPriceMapper;
import com.capitole.product.price.infrastructure.projections.output.ProductPriceODTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ProductPriceControllerTest {

  @Mock private ProductPriceEventHandler productPriceEventHandler;
  @Mock private ProductPriceMapper productPriceMapper;

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private ProductPriceODTO productPriceODTO;

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private ProductPrice productPrice;

  @InjectMocks private ProductPriceController mockController;

  @Test
  @DisplayName(
      "GIVEN: Input data " + "WHEN: calling the controller " + "THEN: returns productPriceODTO")
  void getProductPrice() {
    when(productPriceMapper.customInputToDomain(anyInt(), anyInt(), any()))
        .thenReturn(productPrice);
    when(productPriceEventHandler.handle(any(GetProductPriceByBrandInDateQuery.class)))
        .thenReturn(productPrice);
    when(productPriceMapper.domainToODTO(productPrice)).thenReturn(productPriceODTO);

    ResponseEntity<ProductPriceODTO> responseEntity =
        mockController.getProductPrice(1, "2020-06-14 16:00:00", 3);
    Assertions.assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    assertEquals(ResponseEntity.ok(productPriceODTO), responseEntity);
  }
}
