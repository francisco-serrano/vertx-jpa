package application.controller;

import application.entity.Car;
import application.repository.CarRepository;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CarVerticle extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarVerticle.class);

    @Autowired
    private CarRepository carRepository;

    @Override
    public void start() {
        Router router = Router.router(this.vertx);
        router.get("/cars/ready").handler(this::ready);
        router.get("/cars/:id").handler(this::getCarByPublicId);
        router.post("/cars").handler(this::addCar);

        LOGGER.info("Deploying CarVerticle");

        vertx.createHttpServer().requestHandler(router).listen(8080);
    }

    private void ready(RoutingContext routingContext) {
        JsonObject response = new JsonObject();
        response.put("message", "service ready");

        routingContext.response()
                .setStatusCode(200)
                .putHeader("content-type", "application/json")
                .end(response.encodePrettily());
    }

    private void getCarByPublicId(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response().putHeader("content-type", "application/json");

        String carId = routingContext.request().getParam("id");

//        Car car = this.carController.getCarByPublicId(carId);

        Optional<Car> car = this.carRepository.findByCarPublicId(carId);

        response.setStatusCode(200).end(Json.encode(car.orElse(null)));
    }

    private void addCar(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response().putHeader("content-type", "application/json");

        routingContext.request().bodyHandler(handler -> {
//            Car car = this.carController.addCar(handler.toJsonObject().mapTo(Car.class));
            Car car = handler.toJsonObject().mapTo(Car.class);
            car.setCarPublicId(UUID.randomUUID().toString());

            Car savedCar = this.carRepository.save(car);

            response.setStatusCode(200).end(Json.encode(car));
        });
    }
}
