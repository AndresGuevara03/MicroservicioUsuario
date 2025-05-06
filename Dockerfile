FROM openjdk:23
LABEL authors="david"
WORKDIR /app
COPY target/UsuarioService-0.0.1-SNAPSHOT.jar /app

ENTRYPOINT ["java", "-jar", "UsuarioService-0.0.1-SNAPSHOT.jar"]