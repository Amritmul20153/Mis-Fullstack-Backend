FROM maven:3.9.9-amazoncorretto-17 as build

WORKDIR /build

COPY . .

RUN mvn clean package -DskipTests

FROM amazoncorretto:17

WORKDIR /app

COPY --from=build /build/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "-Dsring.profiles.active=docker","/app/app.jar"]