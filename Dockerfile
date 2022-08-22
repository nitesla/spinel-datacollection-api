FROM openjdk:8-jdk-alpine
MAINTAINER Spinnel consulting
EXPOSE 8080
COPY target/spinel-datacollection-api-1.0.jar spinel-datacollection-api-1.0.jar
ENTRYPOINT ["java","-jar","/spinel-datacollection-api-1.0.jar"]
