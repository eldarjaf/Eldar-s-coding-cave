package com.eldar.location.services;

import com.eldar.location.entities.Location;
import com.eldar.location.repos.LocationRepository;
import com.eldar.location.utilities.EmailUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    EmailUtil emailUtil;

    @Override
    public Location saveLocation(Location location) {
        emailUtil.sendEmail("eldarjaf@gmail.com", "Location saved", "Successfully saved and returned view");
        return locationRepository.save(location);
    }

    @Override
    public Location updateLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public void deleteLocation(Location location) {
        locationRepository.delete(location);
    }

    @Override
    public Location getLocationById(int id) {
        Optional<Location> location = locationRepository.findById(id);
        if (location.isPresent()) {
            return location.get();
        } else {
            System.out.println("Location by " + id + " not found");
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {  //CrudRepository cannot return list, so JpaRepository needed
        return locationRepository.findAll();
    }
}
