INSERT INTO users(user_id, firstname, lastname, email, login, password, role, approved) VALUES
(1, 'Natalia', 'Kowalska', 'natalia@wp.pl', 'natalia', '$2a$10$1tROLPBI9uHXSBJi8lCQU.FwpYn2xKIRyFxpRcA9av10i0CtvERfi', 'ADMIN', true),
(2, 'Alicja', 'Nowak', 'alicja@wp.pl', 'alicja', '$2a$10$/OPwe.yD7aGg5EEpSMTdtOWg.OAUtbeDApgoGlCmAVZzMoScrS4.q', 'ADMIN', true),
(3, 'Przemek', 'Jankowski', 'przemek@wp.pl', 'przemek', '$2a$10$Tw9jW6ZF24MBgWKoEIaeDuz7SjpCNI7UeSi5BTwyf4cjZLdhQ5Wai', 'USER', true),
(4, 'xx', 'cc', 'vv@wp.pl', 'a', '$2a$10$Hhx2PDRt2oIc4Ud8U9B2RO7X4I4P9twaQAlyowqNrWWwJUe0rNwHa', 'ADMIN', false);

INSERT INTO products(product_id, product_name, description, price, user_id, available, add_date) VALUES
(1, 'product1', 'description1', 10.00, 2, true, '2019-03-05'),
(2, 'product2', 'description2', 15.00, 2, false, '2019-03-05'),
(3, 'product3', 'description3', 16.00, 3, true, '2019-03-05'),
(4, 'product4', 'description4', 17.00, 4, true, '2019-03-05'),
(5, 'product5', 'description5', 15.00, 4, false, '2019-03-05'),
(6, 'product6', 'description6', 16.00, 4, true, '2019-03-05');

INSERT INTO orders(order_id, address, user_id) VALUES
(1, 'Gdańsk', 3),
(2, 'Gdańsk', 3);




