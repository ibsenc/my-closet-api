# My Closet API

## Description:
A Spring Boot server with an API for closet management. Users can create, view, and store outfit and article resources. Utilizes image-service to store images of articles/outfits using Amazon S3 and stores object attributes using PostgreSQL.

## Documentation:
To view the Swagger documentation locally, run the server and visit: http://localhost:8080/swagger-ui.html#/

## Figma Design:
[MyCloset Project](https://www.figma.com/file/zruecashcVdPQav36pW8aY/MyCloset-App?node-id=0%3A1&t=ZtTwWu3NJ9I6Eeks-1)

## Starting the Spring Boot server:
### To clean:
`./gradlew clean`

### To run:
`./gradlew bootrun`

## PostgreSQL
### To Access PostgreSQL DB Locally:
`psql --host=localhost --port=5432 --username=YOUR_USERNAME --dbname=postgres --password`
`\c mycloset`

### Create Tables
Run the following command in the terminal to generate the necessary tables in PostgreSQL:
`sh scripts/create_tables.sh`

### Drop All Tables
Run the following command in the terminal to drop all tables in PostgreSQL:
`sh scripts/drop_tables.sh`
