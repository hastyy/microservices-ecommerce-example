FROM openjdk:11
COPY target/discovery-server.jar discovery-server.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "/discovery-server.jar"]