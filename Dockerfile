FROM gradle:7.6.0-jdk11-alpine AS BUILD
COPY . /usr/app
WORKDIR /usr/app
RUN gradle build

FROM openjdk:11-jre-slim
COPY --from=BUILD /usr/app/build/libs/assemblyvoting-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "assemblyvoting-0.0.1-SNAPSHOT.jar"]