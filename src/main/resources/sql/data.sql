INSERT INTO PUBLIC.USERS(USER_ID, FIRSTNAME, LASTNAME, EMAIL, LOGIN, PASSWORD, ROLE, APPROVED) VALUES
(1, 'Natalia', 'Kowalska', 'natalia@wp.pl', 'natalia', 'password', 'ADMIN', true),
(2, 'Alicja', 'Nowak', 'alicja@wp.pl', 'alicja', 'password1', 'ADMIN', true),
(3, 'Przemek', 'Jankowski', 'przemek@wp.pl', 'przemek', 'password2', 'USER', true),
(4, 'xx', 'cc', 'vv@wp.pl', 'bb', 'password4', 'USER', false);
(5, 'xx', 'cc', 'vv@wp.pl', 'a', 'a', 'ADMIN', false);

INSERT INTO PUBLIC.PRODUCTS(PRODUCT_ID, PRODUCT_NAME, DESCRIPTION, PRICE, USER_ID, AVAILABLE) VALUES
(1, 'product1', 'description1', 10.00, 1, true),
(2, 'product2', 'description2', 15.00, 2, true),
(3, 'product3', 'description3', 16.00, 3, true),
(4, 'product4', 'description4', 17.00, 4, false);

INSERT INTO PUBLIC.ORDERS(ORDER_ID, ADDRESS, USER_ID, PRODUCT_ID) VALUES
(1, 'Gdańsk', 1, 1),
(2, 'Gdańsk', 2, 2),
(3, 'Gdańsk', 3, 3),
(4, 'Gdańsk', 1, 4);




