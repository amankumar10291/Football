FROM openjdk:8-jre-alpine3.9

COPY /src/main/resources/config.yaml .
COPY /target/Football-1.0-SNAPSHOT.jar /Football-1.0-SNAPSHOT.jar

EXPOSE 1100 1101 3306
CMD java -server -jar /Football-1.0-SNAPSHOT.jar server config.yaml

