# Stage 1: Build and test Spring Boot application
FROM maven:3.9.7-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
# Download dependencies first for caching
RUN mvn dependency:go-offline -DskipTests

COPY src ./src

# Build package
RUN mvn package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/backend-0.0.1-SNAPSHOT.jar .
EXPOSE 8081
CMD ["java", "-jar", "backend-0.0.1-SNAPSHOT.jar"]