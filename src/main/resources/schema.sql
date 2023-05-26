DROP TABLE IF EXISTS CUSTOMERS;
CREATE TABLE CUSTOMERS (
                      id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                      name VARCHAR(50) NOT NULL,
                      email VARCHAR(50) NOT NULL
);
DROP TABLE IF EXISTS ORDERS;
CREATE TABLE ORDERS(
                          id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                          order_type VARCHAR(20) NOT NULL,
                          price double precision,
                          date timestamp,
                          customer_id int,
                          FOREIGN KEY (customer_id) REFERENCES CUSTOMERS(id)
);