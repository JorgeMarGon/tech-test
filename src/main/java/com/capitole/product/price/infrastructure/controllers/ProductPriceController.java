package com.capitole.product.price.infrastructure.controllers;

import com.capitole.product.price.application.common.ProductPriceEventHandler;
import com.capitole.product.price.application.get_product_price.GetProductPriceByBrandInDateQuery;
import com.capitole.product.price.domain.models.ProductPrice;
import com.capitole.product.price.infrastructure.mappers.ProductPriceMapper;
import com.capitole.product.price.infrastructure.projections.output.ProductPriceODTO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductPriceController {

  private final ProductPriceMapper productPriceMapper;
  private final ProductPriceEventHandler productPriceEventHandler;

  @GetMapping("/{productId}/price")
  public ResponseEntity<ProductPriceODTO> getProductPrice(
      @PathVariable("productId") Integer productId,
      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") String date,
      @RequestParam Integer brandId) {

    ProductPrice product =
        productPriceMapper.customInputToDomain(
            productId,
            brandId,
            LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    ProductPrice productPrice =
        productPriceEventHandler.handle(new GetProductPriceByBrandInDateQuery(product));

    return ResponseEntity.ok(productPriceMapper.domainToODTO(productPrice));
  }
}
