package com.semestrwork.demo.Repository;

import com.semestrwork.demo.Models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository  extends JpaRepository<Car, Long> {

    List<Car> findByBrandCarAndModelCar(String brandCar, String modelCar);

    Optional<Car> findById(Long id);
}
