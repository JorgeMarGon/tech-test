package com.capitole.product.price.infrastructure.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductPriceControllerIntegrationTest {

  @Autowired private MockMvc mvc;

  @CsvSource({
    "2020-06-14 10:00:00, 1, 35.5 EUR",
    "2020-06-14 16:00:00, 1, 25.45 EUR",
    "2020-06-14 21:00:00, 1, 35.5 EUR",
    "2020-06-15 10:00:00, 1, 30.5 EUR",
    "2020-06-16 21:00:00, 1, 38.95 EUR",
  })
  @ParameterizedTest
  void getProductPrice_returnsProductPriceODTO(String date, String brandId, String expectedResult)
      throws Exception {

    mvc.perform(get("/product/35455/price").queryParam("date", date).queryParam("brandId", brandId))
        .andExpect(status().isOk())
        .andExpectAll(jsonPath("$.price").value(expectedResult));
  }
}
