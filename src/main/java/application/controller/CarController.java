package application.controller;

import io.vertx.core.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import application.entity.Car;
import application.repository.CarRepository;

import java.util.Optional;
import java.util.UUID;

@Controller
public class CarController {
    @Autowired
    private CarRepository repository;

    public Car getCar(String id) {
        Optional<Car> car = repository.findById(id);

        return car.orElse(null);
    }

    public Car addCar(Car car) {
        System.out.println(Json.encode(car));

        return repository.save(car);
    }
}
