CREATE TABLE articles (
    id VARCHAR NOT NULL,
    name VARCHAR NOT NULL,
    description VARCHAR NULL,
    imageFileNames VARCHAR ARRAY[4],
    PRIMARY KEY (id)
);