package com.capitole.product.price.infrastructure.adapters.get_product_price;

import com.capitole.common.domain.exceptions.RestApiException;
import com.capitole.product.price.application.get_product_price.ProductPriceRepository;
import com.capitole.product.price.domain.errors.ProductPriceErrorCode;
import com.capitole.product.price.domain.models.ProductPrice;
import com.capitole.product.price.infrastructure.mappers.ProductPriceMapper;
import com.capitole.product.price.infrastructure.persistence.dao.ProductPriceRepositoryJpa;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductPriceRepositoryAdapter implements ProductPriceRepository {

  private final ProductPriceRepositoryJpa productPriceRepositoryJpa;
  private final ProductPriceMapper productPriceMapper;

  @Override
  public List<ProductPrice> findAllByIdAndBrandId(ProductPrice productPrice) {
    return Optional.ofNullable(
            productPriceRepositoryJpa.findByProductIdAndBrand_Id(
                productPrice.getId(), productPrice.getBrand().getId()))
        .map(
            list -> list.stream().map(productPriceMapper::jpaToDomain).collect(Collectors.toList()))
        .filter(list -> !list.isEmpty())
        .orElseThrow(
            () ->
                new RestApiException(ProductPriceErrorCode.PRICE_NOT_FOUND, HttpStatus.NOT_FOUND));
  }
}
