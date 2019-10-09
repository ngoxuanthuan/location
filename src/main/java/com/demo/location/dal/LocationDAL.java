package com.demo.location.dal;

import com.demo.location.model.Location;

public interface LocationDAL {
    Location addNewLocation(Location location);

    Location updateLocation(Location location);

    boolean deleteLocation(Long locationId);

    Location getSpecificByName(String locationName);
    Location getSpecificByid(Long locationId);
}
