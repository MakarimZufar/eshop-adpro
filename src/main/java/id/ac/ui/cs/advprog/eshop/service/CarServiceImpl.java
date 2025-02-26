package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService { // Complexity is 4 Everything is cool!
    private final CarRepository carRepository;

    // Constructor injection (lebih baik daripada @Autowired di field)
    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car create(Car car) {
        return carRepository.create(car);
    }

    @Override
    public List<Car> findAll() { // Complexity is 3 Everything is cool!
        return carRepository.findAll(); // Langsung pakai List<Car>, tidak perlu pakai Iterator
    }

    @Override
    public Car findById(String carId) {
        return carRepository.findById(carId);
    }

    @Override
    public void update(String carId, Car car) {
        carRepository.update(carId, car);
    }

    @Override
    public void deleteCarById(String carId) {
        carRepository.delete(carId);
    }
}
