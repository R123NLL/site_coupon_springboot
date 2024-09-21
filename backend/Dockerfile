# Backend build stage
FROM maven:3.8.5-openjdk-17-slim as backend-builder
WORKDIR /app

# Copy pom.xml and download dependencies
COPY ./pom.xml ./
RUN mvn dependency:go-offline -B

# Copy the source code and build the app
COPY ./src ./src
RUN mvn clean package -DskipTests

# Final stage: Combine frontend and backend
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the built jar from the backend-builder stage
COPY --from=backend-builder /app/target/*.jar app.jar

# Set environment variables (adjust as necessary)
ENV SPRING_DATASOURCE_URL=jdbc:mysql://db:3307/site_coupons_springboot
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=12345678
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

# Expose the port the Spring Boot app will run on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
