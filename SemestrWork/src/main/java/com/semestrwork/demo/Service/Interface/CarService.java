package com.semestrwork.demo.Service.Interface;

import com.semestrwork.demo.Models.Car;
import com.semestrwork.demo.Models.User;

import java.util.List;

public interface CarService extends ForAllService {

    List<Car> getAllCars();

    List<Car> getFilterCars(String brand, String model, String minCost, String maxCost);

    Car getCarFromAllCars(Long id_car);

    boolean addCar(Car car);

    boolean removeCar(Long idOfCar);
}
