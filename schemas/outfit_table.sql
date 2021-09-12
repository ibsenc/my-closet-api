CREATE TABLE outfits (
    id VARCHAR NOT NULL,
    name VARCHAR NOT NULL,
    description VARCHAR NULL,
    imageFileNames VARCHAR ARRAY[4],
    articleIds VARCHAR ARRAY[20],
    PRIMARY KEY (id)
);