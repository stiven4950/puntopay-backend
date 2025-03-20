FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY build/libs/torrefactora-1.0.jar app.jar

EXPOSE 8080

CMD ["java", "-jar","-Dspring.profiles.active=pdn", "app.jar"]