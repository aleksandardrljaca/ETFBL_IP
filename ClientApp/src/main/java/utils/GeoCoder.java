package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import dao.daoImpl.LocationDAOImpl;
import models.dto.Location;

public class GeoCoder {
	public static Location getCoordinates(String address) throws Exception {
        // Kodiranje adrese u URL format
        String encodedAddress = URLEncoder.encode(address, "UTF-8");
        String urlString = "https://nominatim.openstreetmap.org/search?q=" + encodedAddress + "&format=json&addressdetails=1&limit=1";

        // Kreiranje URL-a i konekcije
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "JavaGeocodingApp/1.0");

        // Èitanje odgovora
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Ispis odgovora za debug
       //System.out.println("Server Response: " + response.toString());

        // Parsiranje odgovora (ako je JSON format validan)
        if (response.length() == 0 || response.toString().equals("[]")) {
           return null;
        }

        // Pronalaženje koordinata u odgovoru
        String jsonResponse = response.toString();
        int latIndex = jsonResponse.indexOf("\"lat\":\"");
        int lonIndex = jsonResponse.indexOf("\"lon\":\"");

        if (latIndex != -1 && lonIndex != -1) {
            String lat = jsonResponse.substring(latIndex + 7, jsonResponse.indexOf("\"", latIndex + 7));
            String lon = jsonResponse.substring(lonIndex + 7, jsonResponse.indexOf("\"", lonIndex + 7));
            return new LocationDAOImpl().insert(address, Double.parseDouble(lat),Double.parseDouble(lon));
			
        } else {
            return null;
        }
    }
}
