# My Closet API

## Description:
A Spring Boot server with an API for closet management. Users can create, view, and store outfit and article resources. Utilizes image-service to store images of articles/outfits using Amazon S3 and stores object attributes using PostgreSQL.

## Documentation:
To view Swagger documentation locally, visit: http://localhost:8080/swagger-ui.html#/


## Starting Spring Boot server:
### To clean:
`./gradlew clean`

### To run:
`./gradlew bootrun`

## PostgreSQL
### How to access PostgreSQL DB locally:
`psql --host=localhost --port=5432 --username=YOUR_USERNAME --dbname=postgres --password`
`\c mycloset`

### Create Tables
Run the following command in the terminal to generate the necessary tables in PostgreSQL:
`sh scripts/create_tables.sh`

### Drop All Tables
Run the following command in the terminal to drop all tables in PostgreSQL:
`sh scripts/drop_tables.sh`