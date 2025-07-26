# Use Java 21 slim image
FROM eclipse-temurin:21-jdk-jammy

# Set working directory inside the container
WORKDIR /app

# Copy all backend files
COPY . .

# Give execute permission to the Maven wrapper
RUN chmod +x mvnw

# Build the Spring Boot application (skip tests for faster build)
RUN ./mvnw clean package -DskipTests

# Run the generated JAR
CMD ["java", "-jar", "target/backend-0.0.1-SNAPSHOT.jar"]