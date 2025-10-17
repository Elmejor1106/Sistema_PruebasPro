# Stage 1: Build the application with Maven
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn package -DskipTests

# Stage 2: Create the final, smaller runtime image
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/clubes-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8114
ENTRYPOINT ["java", "-jar", "app.jar"]