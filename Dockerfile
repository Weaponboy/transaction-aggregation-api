# ========================
# Build Stage
# ========================
FROM eclipse-temurin:21-jdk-jammy AS builder

WORKDIR /build

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

RUN chmod +x ./gradlew

RUN ./gradlew dependencies --no-daemon

COPY src src

RUN ./gradlew clean bootJar -x test --no-daemon

# ========================
# Runtime Stage
# ========================
FROM eclipse-temurin:21-jre-jammy

RUN groupadd -r spring && useradd -r -g spring spring

WORKDIR /app

COPY --from=builder /build/build/libs/*.jar app.jar

RUN chown -R spring:spring /app

USER spring

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]