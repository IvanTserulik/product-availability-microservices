# Product availability microservices
Spring cloud demo that involving: spring web flux, spring cloud.

Contains 4 services:
- Eureka server registry
- Inventory service contains info about product availability
- Catalog service contains detailed info about items
- Product service merges data from Catalog and Inventory service

## Technical Requirements
- Java 11
- Maven 3.3+
- Git

## How to build
```
mvn clean install
```

## How to run
Make sure that ports those are mentioned in application.properties are free.

Command to run application:
```
java -jar target/artifact-XXX.jar
```