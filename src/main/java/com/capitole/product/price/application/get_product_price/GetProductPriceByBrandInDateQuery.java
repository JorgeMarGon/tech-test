package com.capitole.product.price.application.get_product_price;

import com.capitole.product.price.domain.models.ProductPrice;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GetProductPriceByBrandInDateQuery {
  private final ProductPrice productPrice;
}
