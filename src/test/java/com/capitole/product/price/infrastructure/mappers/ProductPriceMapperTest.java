package com.capitole.product.price.infrastructure.mappers;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.capitole.product.price.domain.models.ProductPrice;
import com.capitole.product.price.infrastructure.persistence.jpa.ProductPriceJpa;
import com.capitole.product.price.infrastructure.projections.output.ProductPriceODTO;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(MockitoExtension.class)
class ProductPriceMapperTest {

  private static PodamFactory factory;
  @Mock private BrandMapper brandMapper;
  @InjectMocks private ProductPriceMapperImpl productPriceMapper;

  @BeforeAll
  public static void setUp() {
    factory = new PodamFactoryImpl();
  }

  @Test
  @DisplayName("GIVEN: ProductPriceJpa WHEN: map to ProductPrice THEN: ProductPrice")
  void productPriceJpaToProductPrice() {
    ProductPriceJpa productPriceJpa = factory.manufacturePojo(ProductPriceJpa.class);
    ProductPrice productPrice = productPriceMapper.jpaToDomain(productPriceJpa);
    assertNotNull(productPrice);
    assertAll(
        () -> assertThat(productPrice.getId()).isEqualTo(productPriceJpa.getProductId()),
        () ->
            assertThat(productPrice.getPrice()).isEqualTo(productPriceJpa.getPrice().doubleValue()),
        () -> assertThat(productPrice.getTariff()).isEqualTo(productPriceJpa.getTariff()),
        () -> assertThat(productPrice.getStartDate()).isEqualTo(productPriceJpa.getStartDate()),
        () -> assertThat(productPrice.getEndDate()).isEqualTo(productPriceJpa.getEndDate()),
        () -> assertThat(productPrice.getPriority()).isEqualTo(productPriceJpa.getPriority()),
        () -> assertThat(productPrice.getCurrency()).isEqualTo(productPriceJpa.getCurrency()));
  }

  @Test
  @DisplayName(
      "GIVEN: productId, brandId and date WHEN: map to ProductPrice THEN: correct ProductPrice")
  void inputRestToDomain() {
    LocalDateTime localDateTime = now();
    ProductPrice productPrice = productPriceMapper.customInputToDomain(1, 2, localDateTime);
    assertNotNull(productPrice);
    assertAll(
        () -> assertThat(productPrice.getId()).isEqualTo(1),
        () -> assertThat(productPrice.getBrand().getId()).isEqualTo(2),
        () -> assertThat(productPrice.getSelectedDate()).isEqualTo(localDateTime));
  }

  @Test
  @DisplayName("GIVEN: ProductPrice WHEN: map to ProductPriceODTO THEN: correct ProductPriceODTO")
  void productPriceToProductPriceODTO() {
    ProductPrice productPrice = factory.manufacturePojo(ProductPrice.class);
    ProductPriceODTO productPriceODTO = productPriceMapper.domainToODTO(productPrice);
    assertNotNull(productPrice);
    assertAll(
        () -> assertThat(productPriceODTO.getProductId()).isEqualTo(productPrice.getId()),
        () -> assertThat(productPriceODTO.getBrandId()).isEqualTo(productPrice.getBrand().getId()),
        () ->
            assertThat(productPriceODTO.getPrice())
                .isEqualTo(productPrice.getPrice() + " " + productPrice.getCurrency()),
        () -> assertThat(productPriceODTO.getTariff()).isEqualTo(productPrice.getTariff()),
        () -> assertThat(productPriceODTO.getStartDate()).isEqualTo(productPrice.getStartDate()),
        () -> assertThat(productPriceODTO.getEndDate()).isEqualTo(productPrice.getEndDate()));
  }
}
