FROM openjdk:11
COPY target/api-gateway.jar /api-gateway.jar
# EXPOSE 8080
# ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "/api-gateway.jar"]
ENV DOCKERIZE_VERSION v0.6.1
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz