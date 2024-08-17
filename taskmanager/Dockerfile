FROM maven:3.9.8-amazoncorretto-21 AS build

WORKDIR /app

# Copy the parent pom.xml
COPY ./pom.xml .

# Copy the child pom.xml and src
COPY ./taskmanager/pom.xml ./taskmanager/
COPY ./taskmanager/src ./taskmanager/src

WORKDIR /app/taskmanager

RUN mvn clean package

FROM amazoncorretto:21-alpine

EXPOSE 8090

RUN mkdir /app

COPY --from=build /app/taskmanager/target/*.jar /app/spring-boot-application.jar

CMD ["java","-jar","/app/spring-boot-application.jar"]
