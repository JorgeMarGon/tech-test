package com.capitole.product.price.application.get_product_price;

import com.capitole.product.price.domain.models.ProductPrice;
import java.util.List;

public interface ProductPriceRepository {
  List<ProductPrice> findAllByIdAndBrandId(ProductPrice productPrice);
}
