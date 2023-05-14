package com.capitole.product.price.infrastructure.persistence.jpa;

import com.capitole.product.price.domain.types.CurrencyType;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;

@ToString
@Data
@Entity
@Table(name = "ProductPrice")
public class ProductPriceJpa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "product_id", nullable = false)
  private Integer productId;

  @Column(nullable = false)
  private Integer tariff;

  @Column(nullable = false)
  private Integer priority;

  @Column(nullable = false)
  private BigDecimal price;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private CurrencyType currency;

  @Column(name = "start_date", nullable = false)
  private LocalDateTime startDate;

  @Column(name = "end_date", nullable = false)
  private LocalDateTime endDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "brand_id", nullable = false)
  private BrandJpa brand;
}
