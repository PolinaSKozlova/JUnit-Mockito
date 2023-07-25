DROP TABLE IF EXISTS books CASCADE;
CREATE TABLE IF NOT EXISTS books (book_id BIGINT PRIMARY KEY, book_name varchar(255) NOT NULL, book_price float);