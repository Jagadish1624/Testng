# Use Maven with Java
FROM maven:3.9.6-eclipse-temurin-17
 
# Set working directory inside container
WORKDIR /app
 
# Copy project files
COPY pom.xml .
COPY src ./src
 
# Download dependencies
RUN mvn dependency:go-offline
 
# Run Selenium tests
CMD ["mvn", "clean", "test"]