CREATE TABLE Brand
(
  id   INT     NOT NULL AUTO_INCREMENT,
  code VARCHAR NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE ProductPrice
(
  id           INT                        NOT NULL AUTO_INCREMENT,
  product_id   INT                        NOT NULL,
  tariff       INT                        NOT NULL,
  priority     INT                        NOT NULL,
  price        DECIMAL(10, 2)             NOT NULL,
  currency     ENUM ('USD', 'EUR', 'GBP') NOT NULL,
  start_date   DATETIME                   NOT NULL,
  end_date     DATETIME                   NOT NULL,
  brand_id     INT                        NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (brand_id) REFERENCES Brand (id)
);