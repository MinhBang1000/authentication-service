# Authentication Service - PS

## Tech

PS uses a number of open source projects to work properly:

- [Spring Project] - Design a backend or services!
- [Postgres] - As a database!

## Installation

PS requires
- JDK 17+
- Docker Desktop

Creating some require files:
1. .env
```sh
POSTGRES_USER=<username>
POSTGRES_PASSWORD=<password>
POSTGRES_DB=<dbname>
```
2. src/main/resources/application.properties
```sh
spring.application.name=<service name>
server.port=<service port>
spring.datasource.url=jdbc:postgresql://<docker container name>:<container port>/<dbname>
spring.datasource.username=<username>
spring.datasource.password=<password>
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
management.endpoints.web.exposure.include=*
```
3. Dockerfile
```sh
FROM openjdk:17

COPY ./target/<your-file>.jar /app/<your-file>.jar

WORKDIR /app/

EXPOSE 8081
EXPOSE 5005

CMD [ "java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "/app/<your-file>.jar" ]
```
4. docker-compose.yml - example | it can be customed all values.
```sh
version: "1.0.0"
services:
  post-postgres-db:
    image: postgres
    container_name: post-postgres-db
    env_file:
      - .env
    ports:
      - '5433:5432'
    networks:
      - post-net
    restart: always
  post-service:
    image: post-service:1.0.0
    container_name: post-service
    build:
      context: .
      dockerfile: Dockerfile
    env_file:
      - .env
    ports:
      - '8081:8081'
      - '5005:5005'
    networks:
      - post-net
    depends_on:
      - post-postgres-db
networks:
  post-net:
    name: post-net
    attachable: true
    driver: bridge
```
