# My Closet API

## Description:
A Spring Boot server with an API for closet management. Users can create, view, and store outfit and article resources. Utilizes image-service to store images of articles/outfits using Amazon S3 and stores object attributes using PostgreSQL.

## Starting Spring Boot server:
### To clean:
`./gradlew clean`

### To run:
`./gradlew bootrun`

## How to access PostgreSQL DB locally
`psql --host=localhost --port=5432 --username=YOUR_USERNAME --dbname=postgres --password`
`\c mycloset`
