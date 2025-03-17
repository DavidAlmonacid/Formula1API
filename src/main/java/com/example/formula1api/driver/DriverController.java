package com.example.formula1api.driver;

import com.example.formula1api.exceptions.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/f1/drivers")
public class DriverController {

    private final DriverService driverService;

    private DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<Driver>> findAll() {
        return ResponseEntity.ok(driverService.findAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Driver> findById(@PathVariable Long id) {
        Driver driver = driverService.findById(id);

        if (driver == null) {
            throw new NotFoundException("Driver with id '" + id + "' was not found");
        }

        return ResponseEntity.ok(driver);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Void> save(@RequestBody Driver driver) throws URISyntaxException {
        if (driver.getName().isBlank()) {
            throw new IllegalArgumentException("Driver name is required");
        }

        var savedDriver = driverService.save(driver);
        var location = new URI("/api/f1/drivers/" + savedDriver.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Driver driver) {
        if (driver.getName().isBlank()) {
            throw new IllegalArgumentException("Driver name is required");
        }

        Driver updatedDriver = driverService.update(id, driver);

        if (updatedDriver == null) {
            throw new NotFoundException("Driver with id '" + id + "' was not found");
        }

        return ResponseEntity.noContent().build();
    }
}
