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
* PostgresSQL
* JSON Web Tokens (JWT)

## Build
    $ git clone https://github.com/ksilisk/chat-rest-service.git
    $ cd chat-rest-service
    $ ./mvnw install
## Run
    $ ./mvnw spring-boot:run
## Tests
    $ ./mvnw test
## Documentation
Documentation for the API is written using `Swagger`.

Default Documentation Path `host:port/docs/swagger-ui.html`

To override path to documentation, you need to change the parameter `springdoc.swagger-ui.path` in `application.yml`

