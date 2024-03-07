## Используем образ Maven для сборки приложения
FROM openjdk:17-jdk-alpine

COPY target/msbtrust-0.0.1-SNAPSHOT.jar msbtrust-1.jar

ENTRYPOINT ["java", "-jar", "msbtrust-1.jar"]



