#FROM openjdk:17-jdk-slim
#WORKDIR /app
#COPY target/*.jar app.jar

#EXPOSE 8080
#ENTRYPOINT ["java","-jar","app.jar"]

# Build Stage
FROM maven:3.9.6-eclipse-temurin-11 AS build
WORKDIR /workspace/app

COPY pom.xml .
RUN mvn -B dependency:go-offline

# 소스 복사 후 빌드
COPY src ./src
RUN mvn -B clean package -DskipTests

# Run Stage
FROM eclipse-temurin:11-jre-alpine
WORKDIR /app

# 빌드 산출물 복사 (fat jar)
COPY --from=build /workspace/app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
