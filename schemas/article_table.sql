CREATE TABLE articles (
    id VARCHAR NOT NULL,
    name VARCHAR NOT NULL,
    description VARCHAR NULL,
    imageFileNames VARCHAR ARRAY[4],
    category VARCHAR NOT NULL,
    PRIMARY KEY (id)
);