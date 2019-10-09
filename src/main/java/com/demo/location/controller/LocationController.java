package com.demo.location.controller;


import com.demo.location.dal.LocationRepository;
import com.demo.location.model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/locations")
public class LocationController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final LocationRepository locationRepository;

    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Location addNewLocation(@RequestBody Location location) {
        LOG.info("Add new location");
        return locationRepository.insert(location);
    }
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Location updateLocation(@RequestBody Location location) {
        LOG.info("Update location ID: {}.", location.getId());
        return locationRepository.save(location);
    }
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteLocation(@PathVariable String locationId) {
        LOG.info("Delete location ID: {}.", locationId);
        locationRepository.deleteById(locationId);
    }

    @RequestMapping(value = "/getSpecific", method = RequestMethod.GET)
    public Object getSpecific(@PathVariable String locationId) {
        LOG.info("Get specific location ID: {}.", locationId);
        return locationRepository.findById(locationId).orElse(null);
    }
}
