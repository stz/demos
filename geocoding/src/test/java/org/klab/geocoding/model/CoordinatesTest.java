package org.klab.geocoding.model;

import org.junit.Test;
import org.klab.geocoding.service.GeocodingServiceException;

public class CoordinatesTest {
    
    @Test
    public void testDistanceFrom() throws GeocodingServiceException {
        Coordinates c1 = new Coordinates(48.815865f, 2.37738f, 0);
        Coordinates c2 = new Coordinates(48.811127f, 2.3833914f, 0);
        System.out.println(c1.distanceFrom(c2));
    }
}
