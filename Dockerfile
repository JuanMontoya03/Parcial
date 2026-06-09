FROM maven:3.9-eclipse-temurin-17 AS build 
WORKDIR /app 
COPY . . 
RUN mvn clean package -DskipTests 
FROM openjdk:17-jdk-slim 
COPY --from=build /app/target/saberpro-0.0.1-SNAPSHOT.jar app.jar 
EXPOSE 8101 
ENTRYPOINT ["java","-jar","app.jar"] 
