FROM java:8u111-jdk

VOLUME /tmp

EXPOSE 8080

ADD ./build/libs/beer-api-0.0.1.jar app.jar

CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]