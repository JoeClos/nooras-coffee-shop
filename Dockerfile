FROM maven:3.9.0-eclipse-temurin-17-alpine AS build
COPY . .
RUN mcv clean package -DskipTests

FROM openjdk:19
COPY --from=build nooras-coffee-shop\target\noora-coffee-shop-0.0.1-SNAPSHOT.jar nooras-coffee-shop\target\noora-coffee-shop-0.0.1-SNAPSHOT.jar

EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "noora-coffee-shop-0.0.1-SNAPSHOT.jar" ]