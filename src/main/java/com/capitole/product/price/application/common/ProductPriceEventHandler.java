package com.capitole.product.price.application.common;

import com.capitole.product.price.application.get_product_price.GetProductPriceByBrandInDateQuery;
import com.capitole.product.price.domain.models.ProductPrice;

public interface ProductPriceEventHandler {

  ProductPrice handle(GetProductPriceByBrandInDateQuery query);
}
