package com.demo.location;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.demo.location.exceptionhandling.ResourceNotFoundException;
import com.demo.location.model.Location;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LocationApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LocationApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port + "/locations";
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testAddNewLocationSuccess() {
        Location location = new Location();
        location.setId("1");
        location.setLat(111111111111d);
        location.setLng(111111111111d);
        location.setLocationName("Ha Noi");
        ResponseEntity<Location> postResponse = restTemplate.postForEntity(getRootUrl() + "/add", location, Location.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

	@Test
	public void testAddNewLocationDuplicateId() {
	}

    @Test
    public void testUpdateLocationFound() {
        String locationId = "1";
        Location location = restTemplate.getForObject(getRootUrl() + "/getSpecific/" + locationId, Location.class);
        location.setLat(11112d);
        location.setLng(22223d);
        location.setLocationName("Ha Noi");
        restTemplate.put(getRootUrl() + "/update/" + locationId, location);

        Location updatedLocation = restTemplate.getForObject(getRootUrl() + "/getSpecific/" + locationId, Location.class);
        assertNotNull(updatedLocation);
        assertEquals(11112d, updatedLocation.getLat());
        assertEquals(22223d, updatedLocation.getLng());
        assertEquals("Ha Noi", updatedLocation.getLocationName());
    }

    @Test
    public void testUpdateLocationNotFound() {
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			restTemplate.put(getRootUrl() + "/update/notFoundLocation", new Location());
		});
    }

	@Test
	public void testDeleteLocationSuccess() {
	}

	@Test
	public void testDeleteLocationNotFound() {
	}

	@Test
	public void testGetSpecificFound() {
	}

	@Test
	public void testGetSpecificNotFound() {
	}

	@Test
	public void testGetLocationInRadius() {
	}
}
