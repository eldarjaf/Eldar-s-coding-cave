package com.eldar.location.controllers;

import com.eldar.location.entities.Location;
import com.eldar.location.services.LocationService;
import com.eldar.location.utilities.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LocationController {

    @Autowired
    LocationService locationService;

    @RequestMapping("/showCreate")
    public String showCreate() {
        return "createLocation";
    }

    @RequestMapping("/saveLoc")
    public String saveLocation(@ModelAttribute("location") Location location, ModelMap model) {
        Location locationSaved = locationService.saveLocation(location);
        String msg = "Location saved with id: " + locationSaved.getId();
        model.addAttribute("message", msg);

        return "createLocation";
    }

    @RequestMapping("/displayLocation")
    public String displayLocations(ModelMap modelMap) {
        List<Location> locations = locationService.getAllLocations();
        modelMap.addAttribute("locations", locations);

        return "displayLocations";
    }

    @RequestMapping("/deleteLocation")
    public String deleteLocation(@RequestParam("id") int id, ModelMap modelMap) {
        //Location location = locationService.getLocationById(id);  it is also possible, but lets reduce the DB calls
        Location location = Location.builder().id(id).build();
        locationService.deleteLocation(location);

        List<Location> locations = locationService.getAllLocations();
        modelMap.addAttribute("locations", locations);

        return "displayLocations";
    }

    @RequestMapping("/showUpdate")
    public String showUpdate(@RequestParam("id") int id, ModelMap modelMap) {
        Location location = locationService.getLocationById(id);
        modelMap.addAttribute("location", location);
        return "updateLocation";
    }

    @RequestMapping("/updateLoc")
    public String updateLocation(@ModelAttribute("location") Location location, ModelMap model) {
        locationService.updateLocation(location);

        List<Location> locations = locationService.getAllLocations();
        model.addAttribute("locations", locations);
        return "displayLocations";
    }

}


