package org.klab.geocoding.service;

public class GeocodingServiceException extends Exception {
    
    private static final long serialVersionUID = -6919210052539826842L;
    
    public GeocodingServiceException(String msg) {
        super(msg);
    }
    
    public GeocodingServiceException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
