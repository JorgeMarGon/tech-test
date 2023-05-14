package com.capitole.product.price.application.get_product_price;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.capitole.product.price.domain.models.ProductPrice;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetProductPriceByBrandInDateQueryHandlerTest {

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private ProductPrice productPrice;

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private GetProductPriceByBrandInDateQuery query;

  @Mock private ProductPriceRepository productPriceRepository;

  @InjectMocks private GetProductPriceByBrandInDateQueryHandler mockHandler;

  @Test
  @DisplayName("GIVEN: GetProductPriceByBrandInDateQuery WHEN handle then return a price")
  void getProductPriceByBrandInDateQueryHandler() {

    when(query.getProductPrice()).thenReturn(productPrice);
    when(productPriceRepository.findAllByIdAndBrandId(any())).thenReturn(List.of(productPrice));
    when(productPrice.findPriorityPrice(List.of(productPrice))).thenReturn(productPrice);

    assertInstanceOf(ProductPrice.class, mockHandler.handle(query));
  }
}
