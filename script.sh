./gradlew build
docker build -t beer-api .
docker run -p 8080:8080 -t beer-api