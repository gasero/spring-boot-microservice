# Spring Boot microservice template

A microservice example implementing using Spring Boot technology stack with Cucumber for BDD and MongoDB as a NoSQL database.

## Overview

This project consists of the following modules.

### spec
With the `spec` module, you can generate an API skeleton using the Swagger specification that is defined in the `swagger.yml`.
#### Generating skeleton
- In the pom.xml change the *container* with your new microservice name (see `<modelPackage>` & `<apiPackage>`)
- Run the following command to generate the API sources:
`mvn -Pswagger clean generate-sources`
- copy api and model classes to service project
- Remove from the api interface the ResponseEntity
- Implement the api using `@RestController`

## service
The `service` module contains the all the layers of the microservice such as controllers, services with business logic, data repositories.

## integrationtest
The `integrationtest` module contains a BDD framework that consists of the Gherkin feature files and the Cucumber runners.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

- JDK
- Maven
- Docker

### Installing

A step by step series of examples that tell you how to get a development env running

- Execute `mvn clean install`

## Running the tests

### Unit tests

- Run `mvn --projects service clean test`
- Or using IntelliJ, right click on the test package and run

### Integration tests
Using docker:
- First start the microservice: `docker-compose up` which should bootstrap mongodb and microservice
- Then run `mvn --projects integrationtest -P integration-tests clean test` 

Using IntelliJ:
- Run `mvn --projects integrationtest -P mongodb docker:run`
- Run `mvn --projects service spring-boot:run`
- Right click on the `integrationtest/com.ibm.project.service.container` and click on `Run all features`

## Deployment

Use your desired deployment way. For example using `docker-compose` as described above.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Bartosz Nadworny** - *Initial work* - [IBM github](https://github.ibm.com/bartosz-nadworny/)
