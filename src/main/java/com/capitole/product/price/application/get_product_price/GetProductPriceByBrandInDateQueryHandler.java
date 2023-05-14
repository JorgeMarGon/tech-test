package com.capitole.product.price.application.get_product_price;

import com.capitole.product.price.application.common.ProductPriceEventHandler;
import com.capitole.product.price.domain.models.ProductPrice;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class GetProductPriceByBrandInDateQueryHandler implements ProductPriceEventHandler {
  private final ProductPriceRepository productPriceRepository;

  public ProductPrice handle(GetProductPriceByBrandInDateQuery query) {

    ProductPrice productPrice = query.getProductPrice();
    List<ProductPrice> productPrices = productPriceRepository.findAllByIdAndBrandId(productPrice);

    return productPrice.findPriorityPrice(productPrices);
  }
}
