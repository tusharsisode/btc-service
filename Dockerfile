FROM openjdk:8-jdk-alpine

ADD build/libs/BTCService*.jar BTCApp.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "BTCApp.jar" ]
