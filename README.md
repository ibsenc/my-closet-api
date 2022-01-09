# My Closet API

## Description:
A Spring Boot server with an API for closet management. Users can create, view, and store outfit and article resources. Utilizes image-service to store images of articles/outfits using Amazon S3 and stores object attributes using PostgreSQL.

## How to access PostgreSQL DB locally
`psql --host=localhost --port=5432 --username=camille --dbname=postgres --password`
`\c mycloset`
