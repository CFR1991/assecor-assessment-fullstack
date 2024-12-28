# Fullstack Test Backend

This project was generated using Java 21 and spring-boot-starter-parent 3.4.1

## Generate sources

To generate files from openapi.yaml, run:

```bash
./mvnw clean generate-sources
```

## Development server

To start a local development server, run:

```bash
./mvnw spring-boot:run
```

## Running unit tests

To execute tests, use the following command:

```bash
./mvnw test
```

## swagger-ui

http://localhost:8080/swagger-ui/index.html

## PUT sample-input.csv

use http://localhost:8080/swagger-ui/index.html#/Person/addPersonsFromFile to upload sample-input.csv 