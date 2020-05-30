package com.eldar.location.controllers;

import com.eldar.location.entities.Location;
import com.eldar.location.repos.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationRESTController {

    @Autowired
    LocationRepository locationRepository; //we do not have any business logic in service so directly
    //connected to data layer

    @GetMapping
    public List<Location> getLocations() {
        return locationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Location getLocation(@PathVariable int id){
        return locationRepository.findById(id).get();
    }

    @PostMapping
    public Location createLocation(@RequestBody Location location) {
        return locationRepository.save(location);
    }

    @PutMapping
    public Location updateLocation(@RequestBody Location location) {
        return locationRepository.save(location);
    }

    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable int id){
        locationRepository.deleteById(id);
    }
}
