# microservice

Rest microservice made up with Quarkus framework (java). This API backend, allows the user to add products. In addittion to that, the database, is configured to connect with a Docker image of mysql. So each microservice has its own mysql db, making the service more funcional, scalable and secure. This microservice puts data that is consumed by consumer microservice, so they are connected. This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to download a mysql image from docker you have to execute the command

```
docker pull mysql
```

After that, just run the image and config the properties file to connect in the localhost
If you want to have two microservices and connect with the correlated microservice (product microservice), you will have to create another db, in a separate docker image, so each microservice can be independent. To do that, you will have to create a docker-compose file. I give you this example, in wich I create two mysql images and I assign two different ports. Then, in the config, in each microservice, i choose the port in wich I have to connect to the DB

```
version: '3'
services:
  mysql1:
    image: mysql
    environment:
     MYSQL_ROOT_PASSWORD: admin
     MYSQL_USER: admin
     MYSQL_HOST: localhost
     MYSQL_PASSWORD: admin
     MYSQL_DATABASE: product_db
    ports:
      - "3307:3306"
  mysql2:
    image: mysql
    environment:
     MYSQL_ROOT_PASSWORD: admin
     MYSQL_USER: admin
     MYSQL_HOST: localhost
     MYSQL_PASSWORD: admin
     MYSQL_DATABASE: customer_db
    ports:
      - "3308:3306"
```

After this, you should execute the command

```
docker-compose up -d
```


## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/microservice-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- Hibernate ORM ([guide](https://quarkus.io/guides/hibernate-orm)): Define your persistent model with Hibernate ORM and JPA
- RESTEasy Reactive ([guide](https://quarkus.io/guides/resteasy-reactive)): A JAX-RS implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.

## Provided Code

### Hibernate ORM

Create your first JPA entity

[Related guide section...](https://quarkus.io/guides/hibernate-orm)



### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
