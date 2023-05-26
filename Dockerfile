FROM openjdk:17-alpine
MAINTAINER example.com
COPY target/photo-booth-0.0.1-SNAPSHOT.jar photo-booth-0.0.1.jar
ENTRYPOINT ["java","-jar","/photo-booth-0.0.1.jar"]