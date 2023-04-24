FROM maven:3.9.0-eclipse-temurin-17-alpine AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17
COPY --from=build /target/noora-coffee-shop-0.0.1-SNAPSHOT.jar noora-coffee-shop.jar

EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "noora-coffee-shop.jar" ]