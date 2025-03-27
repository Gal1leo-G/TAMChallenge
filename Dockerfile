FROM openjdk:21
COPY ./target/animal-0.0.1-SNAPSHOT.jar animal.jar
ENTRYPOINT ["java","-jar","/animal.jar"]