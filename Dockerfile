FROM openjdk:8-jdk-alpine
MAINTAINER IktiarAli
COPY target/online-bookstore-app-0.0.1-SNAPSHOT.jar online-bookstore-app-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","online-bookstore-app-0.0.1-SNAPSHOT.jar"]