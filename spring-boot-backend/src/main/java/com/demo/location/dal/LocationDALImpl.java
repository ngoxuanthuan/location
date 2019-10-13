package com.demo.location.dal;

import com.demo.location.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class LocationDALImpl implements LocationDAL {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Location addNewLocation(Location location) {
        mongoTemplate.insert(location);
        // Now, location object will contain the ID as well
        return location;
    }

    @Override
    public Location updateLocation(Location location) {
        mongoTemplate.save(location);
        return location;
    }

    @Override
    public boolean deleteLocation(Long locationId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(locationId));
        mongoTemplate.remove(query, Location.class);
        return true;
    }

    @Override
    public Location getSpecificByName(String locationName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("locationName").is(locationName));
        Location location = mongoTemplate.findOne(query, Location.class);
        return location;
    }

    @Override
    public Location getSpecificByid(Long locationId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("locationId").is(locationId));
        Location location = mongoTemplate.findOne(query, Location.class);
        return location;
    }
}
