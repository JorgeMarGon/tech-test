package com.capitole.product.price.infrastructure.mappers;

import com.capitole.product.price.domain.models.Brand;
import com.capitole.product.price.infrastructure.persistence.jpa.BrandJpa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BrandMapper {

  Brand jpaToDomain(BrandJpa brandJpa);
}
