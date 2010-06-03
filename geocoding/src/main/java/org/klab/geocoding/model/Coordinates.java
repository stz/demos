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
    
    /**
     * Caclule la distance en kilomètre en utilisant la formule d'Haversine.
     * @param coord
     * @return
     */
    public float distanceFrom(Coordinates coord) {
        double earthRadius = 3958.75;
        double dLat = Math.toRadians(coord.getLatitude() - this.latitude);
        double dLng = Math.toRadians(coord.getLongitude() - this.longitude);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(coord.getLatitude())) * Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = earthRadius * c;
        return new Float(dist).floatValue();
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
