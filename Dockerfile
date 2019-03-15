FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
EXPOSE 8675
RUN apk update && apk add curl
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/urandom", "-jar", "/app.jar"]
