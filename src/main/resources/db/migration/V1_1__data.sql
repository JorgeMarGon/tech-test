INSERT INTO Brand (id, code)
VALUES (1, 'ZARA');

INSERT INTO ProductPrice (product_id, tariff, priority, price, currency, start_date, end_date, brand_id)
VALUES (35455, 1, 0, 35.50, 'EUR', '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1),
       (35455, 2, 1, 25.45, 'EUR', '2020-06-14 15:00:00', '2020-06-14 18:30:00', 1),
       (35455, 3, 1, 30.50, 'EUR', '2020-06-15 00:00:00', '2020-06-15 11:00:00', 1),
       (35455, 4, 1, 38.95, 'EUR', '2020-06-15 16:00:00', '2020-12-31 23:59:59', 1);