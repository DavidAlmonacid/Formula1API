FROM eclipse-temurin:21 as builder
WORKDIR /app
COPY . .
RUN ./gradlew build -x test

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]
