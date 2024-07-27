FROM openjdk:17.0.1-jdk

EXPOSE 8080

COPY ./target/*.jar /usr/app
WORKDIR /usr/app

CMD["java", "-jar", "java-maven-app-1.1.0-SNAPSHOT.jar"]