package GoogleMaps;
/**
 * Class that holds the necessary GeocodeCoordinates latitude and longitude of the "normal" (entered by user) address
 * as floats to be used for radius search etc.
 * @author Amalie Wilke; StudentID: 1304925
 */
public class GeocodeCoordinates {

    protected float longitude;
    protected float latitude;

    public GeocodeCoordinates(float longitude, float latitude) {
        this.longitude = longitude;
        this.latitude=latitude;
    }

    public GeocodeCoordinates() {

    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
}
