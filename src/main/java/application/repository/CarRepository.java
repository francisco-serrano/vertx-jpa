package application.repository;

import application.entity.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CarRepository extends CrudRepository<Car, Integer> {


    Optional<Car> findByCarPublicId(String id);
}
