# Usar gradle 7.2 con JDK 17 como imagen base
FROM gradle:7.6.1-jdk17 as builder
COPY . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean build --no-daemon -Dorg.gradle.vfs.watch=false

# Cambiar a la imagen de Java para ejecutar la aplicación
FROM openjdk:17-jdk
COPY --from=builder /home/gradle/src/build/libs/*.jar /app/app.jar
CMD ["java", "-jar", "/app/app.jar"]