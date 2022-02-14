package GoogleMaps;
/**
 * Class that holds the necessary GeocodeCoordinates latitude and longitude of the "normal" (entered by user) address
 * as floats to be used for radius search etc.
 * @author Amalie Wilke; StudentID: 1304925
 */
public class GeocodeCoordinates {

    protected float longitude;
    protected float latitude;

    /**
     * Constructor of the Class with given attributes
     * @param longitude
     * @param latitude
     */
    public GeocodeCoordinates(float longitude, float latitude) {
        this.longitude = longitude;
        this.latitude=latitude;
    }

    /**
     * Standard constructor
     */
    public GeocodeCoordinates() {

    }

    /**
     * get the float longitude
     * @return
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * set the float longitude
     * @param longitude
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    /**
     * get the float latitude
     * @return
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * set the float latitude
     * @param latitude
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
}
