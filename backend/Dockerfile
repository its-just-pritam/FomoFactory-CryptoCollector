# Build stage
FROM --platform=linux/amd64 maven:3.9.8-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Final image
FROM --platform=linux/amd64 eclipse-temurin:17-jdk-alpine
RUN echo 'http://dl-3.alpinelinux.org/alpine/v3.12/main' >> etc/apk/repositories
RUN apk upgrade
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
COPY --chown=appuser:appgroup --from=build /app/target/ff.backend-*.jar app.jar
USER appuser
RUN chmod 645 /app.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/app.jar", "-Xmx1024m"]