FROM maven:3.9.8-amazoncorretto-21 AS build

WORKDIR /app

# Copy root pom.xml
COPY ./pom.xml .

# Copy processes pom.xml
COPY ./processes/pom.xml ./processes/

# Copy order pom.xml and src
COPY ./processes/order/pom.xml ./processes/order/
COPY ./processes/order/src ./processes/order/src

WORKDIR /app/processes/order

RUN mvn clean package

FROM amazoncorretto:21-alpine

EXPOSE 8091

RUN mkdir /app

COPY --from=build /app/processes/order/target/*.jar /app/spring-boot-application.jar

CMD ["java","-jar","/app/spring-boot-application.jar"]
