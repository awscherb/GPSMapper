package com.awscherb.gps;

/**
 * Represents a point on a map, given by GPS coordinates
 * Fields for label, type, and custom rotation for label
 * Fields 
 * @author Alex Scherb
 * @version 2/14/14
 */
public class MapPoint {
    
    // A GPS coordinate will have the form:
    // (latitude, longitude), for example:
    // (42.395428,-71.142483)
    // Latitude must be from -90.0 to 90.0
    // Longitude must be from -180.0 to 180.0
    
    /** The latitude */
    double lat;
    /** The longitude */
    double lon;
    /** Label for the point */
    String label;
    /** Type of point */
    int type;
    /** Rotation for label (degrees) */
    int rotation = 0;
    
    /**
     * Public constructor
     * @param lat the latitude
     * @param lon the longitude
     * @param label the label of the point
     * @param int the type
     * @throws GPSOutOfBoundsException if the constructor is called on 
     * an invalid input
     */
    public MapPoint(double lat, double lon, String label, int type) {
        if ((-90.0 <= lat && lat <= 90)
            && (-180.0 <= lon && lon <= 180)) {
        this.lat = lat;
        this.lon = lon;
        this.label = label;
        this.type = type;
        }
        else throw new GPSOutOfBoundsException(); // Not a valid input
    }
    
    public MapPoint(double lat, double lon, String label) {
        if ((-90.0 <= lat && lat <= 90)
            && (-180.0 <= lon && lon <= 180)) {
        this.lat = lat;
        this.lon = lon;
        this.label = label;
        this.type = 0;
        }
        else throw new GPSOutOfBoundsException(); // Not a valid input
    }
    
    /**
     * Constructor without label
     * @param lat
     * @param lon
     */
    public MapPoint(double lat, double lon, int type) {
        if ((-90.0 <= lat && lat <= 90)
            && (-180.0 <= lon && lon <= 180)) {
        this.lat = lat;
        this.lon = lon;
        this.label = "";
        this.type = type;
        }
        else throw new GPSOutOfBoundsException(); // Not a valid input
    }
    
    public MapPoint(double lat, double lon) {
        if ((-90.0 <= lat && lat <= 90)
            && (-180.0 <= lon && lon <= 180)) {
        this.lat = lat;
        this.lon = lon;
        this.label = "";
        this.type = 0;
        }
        else throw new GPSOutOfBoundsException(); // Not a valid input
    }
    
    /**
     * Returns the <code>String</code> representation
     * @return <code>String</code> representation in the form:
     * (lat, lon)
     */
    public String toString() {
        return "(" + lat +", " + lon +")";
    }
    
    /**
     * Compares the given object to this
     * @param o the object we want to compare
     * @return boolean result
     */
    public boolean equals(Object o) {
        if (o instanceof MapPoint) {
            MapPoint temp = (MapPoint) o;
            return temp.lat == this.lat
                    && temp.lon == this.lon;
        }
        else return false;
    }
    
    /** Set the rotation */
    public void setRotation(int r) {
        this.rotation = r;
    }
    
    /** Get lat */
    public double getLat() {
        return this.lat;
    }
    
    /** Get longitude */
    public double getLon() {
        return this.lon;
    }
    
    /** 
     * Exception to be thrown if we attempt to create a GPSPoint 
     * with an invalid input.
     */
    class GPSOutOfBoundsException extends RuntimeException {
        private static final long serialVersionUID = 1L; }

}
