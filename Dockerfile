FROM openjdk:17.0.1-jdk

EXPOSE 8080

COPY ./target/java-maven-app-*.jar /usr/app/
WORKDIR /usr/app

RUN ls -la /usr/app

CMD  java -jar java-maven-app-*.jar
