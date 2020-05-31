package com.semestrwork.demo.Service.Implementation;

import com.semestrwork.demo.Models.Car;
import com.semestrwork.demo.Models.User;
import com.semestrwork.demo.Repository.CarRepository;
import com.semestrwork.demo.Repository.UsersRepository;
import com.semestrwork.demo.Service.Implementation.ServiceImpl;
import com.semestrwork.demo.Service.Interface.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl extends ServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> getFilterCars(String brand, String model, String minCost, String maxCost) {

        List<Car> filterCars = carRepository.findByBrandCarAndModelCar(brand, model);

        return filterCars.stream().filter(car -> car.getCost() > Integer.parseInt(minCost) && car.getCost() <= Integer.parseInt(maxCost)).collect(Collectors.toList());
    }

    @Override
    public Car getCarFromAllCars(Long id_car) {
        return carRepository.findById(id_car).get();
    }

    @Override
    public boolean addCar(Car car) {
        carRepository.save(car);
        return true;
    }

    @Override
    public boolean removeCar(Long idOfCar) {
        carRepository.delete(carRepository.findById(idOfCar).get());
        return true;
    }
}
