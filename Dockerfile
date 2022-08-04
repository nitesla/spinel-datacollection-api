FROM openjdk:8-jdk-alpine
MAINTAINER Spinnel consulting
EXPOSE 8080
COPY target/sabi-datacollection-api-1.0.jar sabi-datacollection-api-1.0.jar
ENTRYPOINT ["java","-jar","/sabi-datacollection-api-1.0.jar"]
