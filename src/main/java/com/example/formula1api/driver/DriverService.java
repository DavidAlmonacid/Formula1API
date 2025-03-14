package com.example.formula1api.driver;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    private DriverRepository driverRepository;

    private DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public List<Driver> findAll() {
        return (List<Driver>) driverRepository.findAll();
    }

    public Driver findById(Long id) {
        return driverRepository.findById(id).orElse(null);
    }

    public Driver save(Driver newDriver) {
        return driverRepository.save(newDriver);
    }

}
