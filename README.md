# Chat Rest Service
* [Description](#description)
* [Build](#build)
* [Run](#run)
* [Tests](#tests)
* [Documentation](#documentation)
## Description
REST API for simple chat with JWT-authentication

### Tech stack
* Java 17
* Maven
* Spring Boot
  * Web
  * Data Jpa
  * Validation
  * Security
* PostgreSQL
* Swagger
* JSON Web Tokens (JWT)

## Build
    $ git clone https://github.com/ksilisk/chat-rest-service.git
    $ cd chat-rest-service
    $ ./mvnw install
## Run
Before running the application you need to create the tables in the database.

To create them you just need to run the DDL-script in `postgres-ddl-script.sql`

Also, before starting, you need to configure the `application.yml`

After that, to run the application, use the command:

    $ ./mvnw spring-boot:run
## Tests
    $ ./mvnw test
## Documentation
Documentation for the API is written using `Swagger`

Default documentation path: `host:port/docs/swagger-ui.html`

To override path to documentation, you need to change the parameter `springdoc.swagger-ui.path` in `application.yml`

