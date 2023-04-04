FROM openjdk:8
EXPOSE 5051
ADD target/DockerWebServer.jar DockerWebServer.jar
ENTRYPOINT ["java", "-jar", "/DockerWebServer.jar"]