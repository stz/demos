package org.klab.geocoding.service;

import org.klab.geocoding.model.Coordinates;

public interface GeocodingService {
    
    Coordinates geocode(String address) throws GeocodingServiceException;
}
