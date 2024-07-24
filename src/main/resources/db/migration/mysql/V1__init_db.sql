CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    stock INT,
    name VARCHAR(255),
    price FLOAT,
    description VARCHAR(1000)
);

CREATE TABLE book_authors (
    book_id INT,
    author_ids INT,
    PRIMARY KEY (book_id, author_ids),
    FOREIGN KEY (book_id) REFERENCES books(id)
);
