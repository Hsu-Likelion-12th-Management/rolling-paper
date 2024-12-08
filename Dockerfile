FROM openjdk:17
COPY ./build/libs/rolling-paper-0.0.1-SNAPSHOT.jar rolling-paper.jar
ENTRYPOINT ["java", "-jar", "rolling-paper.jar"]