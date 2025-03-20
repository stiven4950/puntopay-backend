FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY build/libs/*-SNAPSHOT.jar app.jar

EXPOSE 3000

CMD ["java", "-jar","-Dspring.profiles.active=pdn", "app.jar"]