package com.instant.service.geo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * @author sroshchupkin
 */

@Service
public class GeoCoderServiceImpl implements GeoCoderService, Serializable {

    private static final String GOOGLE_ADRESS_QUERY = "http://maps.googleapis.com/maps/api/geocode/json?address=";

    @Override
    public double[] getGeoPointFromAddress(String locationAddress) {
        double[] locationPoint=null;
        String locationAddres = locationAddress.replaceAll(" ", "%20");
        String str = GOOGLE_ADRESS_QUERY
                + locationAddres + "&sensor=true";
        JSONObject json;
        try {
            double lat, lon;
            json = readJsonFromUrl(str);
            JSONObject geoMetryObject;
            JSONObject locations;
            JSONArray jarr = json.getJSONArray("results");
            int i;
            for (i = 0; i < jarr.length(); i++) {
                json = jarr.getJSONObject(i);
                geoMetryObject = json.getJSONObject("geometry");
                locations = geoMetryObject.getJSONObject("location");
                lat = locations.getDouble("lat");
                lon = locations.getDouble("lng");
                locationPoint = new double[]{lat,lon};
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return locationPoint;
    }


    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
}
