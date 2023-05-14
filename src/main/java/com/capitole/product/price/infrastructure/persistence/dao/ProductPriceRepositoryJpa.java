package com.capitole.product.price.infrastructure.persistence.dao;

import com.capitole.product.price.infrastructure.persistence.jpa.ProductPriceJpa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceRepositoryJpa extends JpaRepository<ProductPriceJpa, Integer> {

  List<ProductPriceJpa> findByProductIdAndBrand_Id(Integer productId, Integer brandId);
}
