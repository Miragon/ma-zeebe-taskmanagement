FROM amazoncorretto:21-alpine

EXPOSE 9101

RUN mkdir /app

COPY ./target/*.jar /app/spring-boot-application.jar

CMD ["java","-jar","/app/spring-boot-application.jar"]
