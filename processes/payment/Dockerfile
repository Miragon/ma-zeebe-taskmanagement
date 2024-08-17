FROM maven:3.9.8-amazoncorretto-21 AS build

WORKDIR /app

# Copy root pom.xml
COPY ./pom.xml .

# Copy processes pom.xml
COPY ./processes/pom.xml ./processes/

# Copy order pom.xml and src
COPY ./processes/payment/pom.xml ./processes/payment/
COPY ./processes/payment/src ./processes/payment/src

WORKDIR /app/processes/payment

RUN mvn clean package

FROM amazoncorretto:21-alpine

EXPOSE 8092

RUN mkdir /app

COPY --from=build /app/processes/payment/target/*.jar /app/spring-boot-application.jar

CMD ["java","-jar","/app/spring-boot-application.jar"]
