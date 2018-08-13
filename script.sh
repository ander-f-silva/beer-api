#!/bin/sh

./gradlew build

docker rmi -f beer-api
docker rm  -f beer
docker build -t beer-api .
docker run --name beer -p 8080:8080 -t beer-api