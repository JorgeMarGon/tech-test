package com.capitole.product.price.infrastructure.mappers;

import com.capitole.product.price.domain.models.ProductPrice;
import com.capitole.product.price.infrastructure.persistence.jpa.ProductPriceJpa;
import com.capitole.product.price.infrastructure.projections.output.ProductPriceODTO;
import java.time.LocalDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = BrandMapper.class)
public interface ProductPriceMapper {

  @Mapping(source = "productId", target = "id")
  @Mapping(source = "brandId", target = "brand.id")
  ProductPrice customInputToDomain(Integer productId, Integer brandId, LocalDateTime selectedDate);

  @Mapping(source = "productId", target = "id")
  ProductPrice jpaToDomain(ProductPriceJpa productPriceJpa);

  @Mapping(source = "id", target = "productId")
  @Mapping(source = "brand.id", target = "brandId")
  @Mapping(
      target = "price",
      expression = "java(productPrice.getPrice() + \" \" + productPrice.getCurrency())")
  ProductPriceODTO domainToODTO(ProductPrice productPrice);
}
