# ---- Build stage ----
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY pom.xml .
RUN mvn -q -B -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -B -DskipTests package

# ---- Run stage ----
FROM eclipse-temurin:17-jre
RUN useradd -ms /bin/bash appuser
USER appuser
WORKDIR /app
COPY --from=build /workspace/target/*-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENV JAVA_OPTS=""
ENV SPRING_DATA_MONGODB_URI="mongodb://localhost:27017/bookstore"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]