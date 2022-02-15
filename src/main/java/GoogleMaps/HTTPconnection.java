package GoogleMaps;
/**
 * HTTP Request to connect to the Google Maps API to return latitude and longitude via geocoding (sending an normalized address format to return its geocordinates)
 * The HTTP request returns a Json file with a large amount of geodata for the specified address, I parse it using the streaming method because I only want two specific data points
 * latitude and longitude
 * I connect to Google Maps using my API key that keeps track of requests and usage and have adapted the URL to accept variables instead of a static format
 * @author Amalie Wilke; StudentID: 1304925
 */
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPconnection {

    //Method to send HTTP request to Google Maps API and receive geocoding information of specified normal address (street, house number, zip code, town) and then parse the returned Json file into workable data
    //Method will return an object of class GeocodeCoordinates that contain longitude and latitude of address as floats
    /**
     * Method to send HTTP request to Google Maps API and receive geocoding information of specified normal address (street, house number, zip code, town) and then parse the returned Json file into workable data
     * Method will return an object of class GeocodeCoordinates that contain longitude and latitude of address as floats
     * @param st
     * @param house
     * @param zi
     * @param town
     * @return
     */
    public GeocodeCoordinates createRequest(String st, int house, int zi, String town) {

        GeocodeCoordinates coord= new GeocodeCoordinates();
        HttpURLConnection connection = null;
        BufferedReader reader;
        String line;
        StringBuffer responseContent=new StringBuffer();
        try{
            //Geocoding request specifications: address=%s(street)%20(deliminator between address elements)%i(housenumber)%20%i(zip)%20%s(town)
            String s=st;
            String t=town;
            String k="AIzaSyB6PzPL35eIKA2W9wKjqdpJizKIGEub5hI";  //This API key will be deleted before every push to GitHub because I do not want it publically accessible, will be given to team members privately and securely
            int hn=house;
            int zip=zi;

            URL url=new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" +s+"%20"+hn+"%20"+zip+"%20"+t+"&key="+k);
            connection=(HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status=connection.getResponseCode();

            if(status>299){
                reader=new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((line=reader.readLine()) !=null){
                    responseContent.append(line);
                }
                reader.close();
            }else{reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line=reader.readLine()) !=null){
                    responseContent.append(line);
                }
                reader.close();
            }
            System.out.println(responseContent);
            coord=parseToDatabase(responseContent.toString());
        }catch(MalformedURLException e){
            System.out.println("Malformed Url");
        }catch(IOException e){
            System.out.println("Connection went wrong");
        }finally{
            connection.disconnect();
        }
        return coord;
    }

    /**
     * Method is used to extract the longitude and latitude out of the json file returned by the Google API.
     * @param responseBody
     * @return
     */
    public static GeocodeCoordinates parseToDatabase(String responseBody) throws JSONException {
        //Parsing the Json file received to extract longitude and latitude
        JSONObject obj=new JSONObject(responseBody);  //create Json Object from responsebody string
        JSONArray obj_arr=obj.getJSONArray("results");  //transfer Json object into an array as the key value of the format is array
        System.out.println(obj_arr);  //double checking all important information was transcribed
        System.out.println("Array lenght of the Json array:"+obj_arr.length());  //double checking I parsed correctly
        float lon=obj_arr.getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getFloat("lng");  //for seperating the relevant values from the data stream I work through the Json array and the objects inside to the key value longitude
        float lat=obj_arr.getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getFloat("lat");  //same as above but for the key value lat
        System.out.println("Longitude:"+lon);
        System.out.println("Latitude:"+lat);

        //creating a new instance of the Geocode Coordinates class that holds latitude and longitude
        GeocodeCoordinates c= new GeocodeCoordinates();

        c.latitude=lat;
        c.longitude=lon;

        return c;
    }

    
}
