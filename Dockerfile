FROM java:8
EXPOSE 8085
ADD /target/url-shortening-service-0.0.1-SNAPSHOT.jar url-shortening-service.jar
ENTRYPOINT ["java", "-jar", "url-shortening-service.jar"]