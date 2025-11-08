FROM gradle:8.7-jdk17 AS build
USER root
RUN apt-get update && apt-get install -y ca-certificates && update-ca-certificates
WORKDIR /app
COPY . /app
RUN gradle clean bootJar --no-daemon -x validateStructure

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/applications/app-service/build/libs/*.jar ms-user.jar
EXPOSE 8082
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=70"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS  -jar ms-user.jar" ]
