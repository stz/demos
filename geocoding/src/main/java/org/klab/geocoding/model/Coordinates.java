package org.klab.geocoding.model;

public class Coordinates {
    
    private float latitude;
    
    private float longitude;
    
    private float altitude;

    public Coordinates() {
        super();
    }

    public Coordinates(float latitude, float longitude, float altitude) {
        super();
        this.altitude = altitude;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getAltitude() {
        return altitude;
    }

    public void setAltitude(float altitude) {
        this.altitude = altitude;
    }
    
    
}
