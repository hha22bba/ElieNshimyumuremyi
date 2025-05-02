# Use the official lightweight JDK image
FROM openjdk:21-slim

# Create a working directory inside the container
WORKDIR /app

# Copy everything into /app
COPY . .

# Compile all .java files inside src/ and output classes to /app/out
RUN find src -name "*.java" > sources.txt && javac -d out @sources.txt

# Run the main class
CMD ["java", "-cp", "out", "main.Main"]
