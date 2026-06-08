FROM openjdk:17
COPY "./target/saberpro-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8101
ENTRYPOINT ["java","-jar","app.jar"]