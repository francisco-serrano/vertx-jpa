## 1. Extend vert.x image
#FROM vertx/vertx3
#
## 2. Environment Variables
#ENV VERTICLE_NAME application.controller.CarVerticle
#ENV VERTICLE_FILE target/sampleapi-1.0-SNAPSHOT-fat.jar
#
## 3. Set the location of the verticles
#ENV VERTICLE_HOME /usr/verticles
#
## 4. Set port
#EXPOSE 8080
#
## 5. Copy your verticle to the container
#COPY $VERTICLE_FILE $VERTICLE_HOME/
#
## 6. Launch the verticle
#WORKDIR $VERTICLE_HOME
#ENTRYPOINT ["sh", "-c"]
#CMD ["exec vertx run $VERTICLE_NAME -cp $VERTICLE_HOME/*"]
FROM openjdk:8
RUN mkdir -p /cars
ADD target/sampleapi-1.0-SNAPSHOT.jar /cars/
EXPOSE 8080
CMD ["java", "-jar", "/cars/sampleapi-1.0-SNAPSHOT.jar"]