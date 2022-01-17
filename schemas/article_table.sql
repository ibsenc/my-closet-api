CREATE TABLE articles (
    id VARCHAR NOT NULL,
    name VARCHAR NOT NULL,
    description VARCHAR NULL,
    imageFileNames VARCHAR ARRAY[4],
    category VARCHAR NOT NULL,
    color VARCHAR NULL,
    seasons VARCHAR ARRAY[4],
    brand VARCHAR NULL,
    size VARCHAR NULL,
    PRIMARY KEY (id)
);