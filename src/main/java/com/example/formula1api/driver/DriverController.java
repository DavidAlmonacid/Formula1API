package com.example.formula1api.driver;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/f1/drivers")
public class DriverController {

    private DriverService driverService;

    private DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    private ResponseEntity<List<Driver>> findAll() {
        return ResponseEntity.ok(driverService.findAll());
    }

    @GetMapping("/{id}")
    private ResponseEntity<Driver> findById(@PathVariable Long id) {
        Driver driver = driverService.findById(id);

        if (driver != null) {
            return ResponseEntity.ok(driver);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    private ResponseEntity<Void> save(@RequestBody Driver driver) throws URISyntaxException {
        Driver savedDriver = driverService.save(driver);
        URI location = new URI("/api/f1/drivers/" + savedDriver.getId());

        return ResponseEntity.created(location).build();
    }

}
