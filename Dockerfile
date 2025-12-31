FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY target/bluegreen-app-1.0.0.jar app.jar
# CMD with sh -c to read environment variable
CMD ["sh", "-c", "java -jar app.jar"]


