# Work in Progress
# Video Game Sales backend - Spring Boot 3
This repository contains the backend for a web application that manages historical video game sales data. 
The backend is built using Java and Spring Boot 3, and it connects to a PostgreSQL database to store and retrieve sales information.
Historical video game sales data can be found here: https://mavenanalytics.io/data-playground?page=2&pageSize=5

## Dependencies
Java 21

Maven 4.0.0

Spring Boot 3.4.1

PostgreSQL 17

## Configuration
The database connection will need to be configured in the application.properties file located in src/main/resources.

```
spring.application.name=VideoGameSalesApp
spring.datasource.url=jdbc:postgresql://localhost:PORT_NUMBER/VideoGameSales
spring.datasource.username=POSTGRE_USERNAME
spring.datasource.password=POSTGRE_PASSWORD
```

A different database connection will need to be configured for testing in the application-test.properties located in 
src/test/resources. 

```
spring.datasource.url=jdbc:postgresql://localhost:PORT_NUMBER/VideoGameSalesTesting
spring.datasource.username=POSTGRE_USERNAME
spring.datasource.password=POSTGRE_PASSWORD
```

## Database Setup
The raw data file used for this project can be found here: https://mavenanalytics.io/data-playground?page=2&pageSize=5

A table will need to be created with the following schema:

| Column Name | Data type | Length/Precision |
| :---- | :---- | :---- |
| id | integer |   |
| title | character varying | 255 |
| console | character varying | 100 |
| genre | character varying | 100 |
| publisher | character varying | 100 |
| developer | character varying | 100 |
| critic_score | real |   |
| total_sales | real |   |
| na_sales | real |   |
| jp_sales | real |   |
| pal_sales | real |   |
| other_sales | real |   |
| release_date | date |   |
| last_update | date |   |

The "id" column should be set as primary key, not null, and identity

## API Endpoints

All endpoints are mapped to "/api/videgames".

Here is an overview of the available API endpoints:

* GET /
Fetch a list of all video games.

* GET /id={id}
Fetch a single video game by id.

* GET /genre={genre}
Fetch a list of all video games of the specified genre.

* POST /
Create a new video game record in the database.

* PUT /id={id}
Update the information of a specific video game.

* DELETE /id={id}
Delete a video game record by its id.


