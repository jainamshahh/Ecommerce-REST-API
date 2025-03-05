-- Passwords format: Password<UserLetter>123
INSERT INTO users (email, first_name, last_name, password, username)
    VALUES ('UserA@junit.com', 'UserA-FirstName', 'UserA-LastName', '$2a$10$hBn5gu6cGelJNiE6DDsaBOmZgyumCSzVwrOK/37FWgJ6aLIdZSSI2', 'UserA')
    , ('UserB@junit.com', 'UserB-FirstName', 'UserB-LastName', '$2a$10$TlYbg57fqOy/1LJjispkjuSIvFJXbh3fy0J9fvHnCpuntZOITAjVG', 'UserB')
    , ('UserC@junit.com', 'UserC-FirstName', 'UserC-LastName', '$2a$10$SYiYAIW80gDh39jwSaPyiuKGuhrLi7xTUjocL..NOx/1COWe5P03.', 'UserC');

INSERT INTO address(address_line_1,address_line_2, city,state, country, user_id)
    VALUES ('123 building1', 'Andheri', 'Mumbai', 'Maharashtra', 'India', 1)
    , ('312 building2', 'Bandra', 'Mumbai', 'Maharashtra', 'India', 3);

INSERT INTO product (name, description, price)
    VALUES ('Product #1', 'Product one short description.', 5.50)
    , ('Product #2', 'Product two short description.', 10.56)
    , ('Product #3', 'Product three short description.', 2.74)
    , ('Product #4', 'Product four short description.', 15.69)
    , ('Product #5', 'Product five short description.', 42.59);

INSERT INTO inventory (product_id, quantity)
    VALUES (1, 5)
    , (2, 8)
    , (3, 12)
    , (4, 73)
    , (5, 2);

INSERT INTO orders (address_id, user_id)
    VALUES (1, 1)
    , (1, 1)
    , (1, 1)
    , (2, 3)
    , (2, 3);

INSERT INTO order_items (order_id, product_id, quantity, selling_price)
    VALUES (1, 1, 5, 5.50)
    , (1, 2, 5, 10.56)
    , (2, 3, 5, 2.74)
    , (2, 2, 5, 10.56)
    , (2, 5, 5, 42.00)
    , (3, 3, 5, 2.74)
    , (4, 4, 5, 15.69)
    , (4, 2, 5, 10.56)
    , (5, 3, 5, 2.74)
    , (5, 1, 5, 5.50);


