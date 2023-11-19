FROM eclipse-temurin:17-jre-ubi9-minimal
COPY ./target/employees-hometask-0.0.1.jar /usr/app/app.jar
WORKDIR /usr/app
EXPOSE 8181
CMD ["java", "-jar", "app.jar"]


