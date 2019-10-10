package com.demo.location.controller;


import com.demo.location.dal.LocationRepository;
import com.demo.location.exceptionhandling.ResourceNotFoundException;
import com.demo.location.model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/locations")
public class LocationController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private static final double EARTH_RADIUS = 3963d;

    private final LocationRepository locationRepository;

    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Location addNewLocation(@RequestBody Location location) {
        LOG.info("Add new location");
        return locationRepository.save(location);
    }

    @RequestMapping(value = "/update/{locationId}", method = RequestMethod.PUT)
    public Location updateLocation(@PathVariable(value = "locationId") String locationId, @RequestBody Location locationDetail) throws ResourceNotFoundException {
        LOG.info("Update location ID: {}.", locationId);
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found for this id :: " + locationId));
        location.setLng(locationDetail.getLng());
        location.setLat(locationDetail.getLat());
        location.setLocationName(locationDetail.getLocationName());
        return locationRepository.save(location);
    }

    @RequestMapping(value = "/delete/{locationId}", method = RequestMethod.DELETE)
    public void deleteLocation(@PathVariable String locationId) throws ResourceNotFoundException {
        LOG.info("Delete location ID: {}.", locationId);
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found for this id :: " + locationId));
        locationRepository.delete(location);
    }

    @RequestMapping(value = "/getSpecific/{locationId}", method = RequestMethod.GET)
    public Object getSpecific(@PathVariable String locationId) throws ResourceNotFoundException {
        LOG.info("Get specific location ID: {}.", locationId);
        return locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found for this id :: " + locationId));
    }

    @RequestMapping(value = "/getLocationInRadius/{radius}", method = RequestMethod.GET)
    public Object getLocationInRadius(@PathVariable double radius, @RequestBody Location currentLocation) {
        LOG.info("Get all location with {} miles base on location: {}.", radius, currentLocation);
        List<Location> allLocations = locationRepository.findAll();
        if (CollectionUtils.isEmpty(allLocations)) {
            return null;
        }
        List<Location> results = new ArrayList<>();
        for (Location location : allLocations) {
            double latDistance = Math.toRadians(currentLocation.getLat() - location.getLat());
            double lngDistance = Math.toRadians(currentLocation.getLng() - location.getLng());
            double a = (Math.sin(latDistance / 2) * Math.sin(latDistance / 2)) +
                    (Math.cos(Math.toRadians(currentLocation.getLat()))) *
                            (Math.cos(Math.toRadians(location.getLat()))) *
                            (Math.sin(lngDistance / 2)) *
                            (Math.sin(lngDistance / 2));
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double dist = EARTH_RADIUS * c;
            if (dist <= radius) {
                results.add(location);
            }
        }
        return results;
    }
}
