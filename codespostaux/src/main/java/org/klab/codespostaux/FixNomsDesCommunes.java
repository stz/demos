package org.klab.codespostaux;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.klab.geocoding.model.Coordinates;
import org.klab.geocoding.service.GeocodingService;
import org.klab.geocoding.service.GeocodingServiceException;
import org.klab.geocoding.service.impl.GoogleGeocodingServiceImpl;

/**
 * Réécrit les noms des communes une orthographe correcte (accents, majuscules, minuscules...) dans le fichier codes_postaux_liste.txt.
 */
public class FixNomsDesCommunes {
    
    public static void main(String[] args) throws IOException {
        
        String tmpFilename = Files.CODES_POSTAUX_FILENAME + ".tmp";
        BufferedWriter out = new BufferedWriter(new FileWriter(tmpFilename));
        
        GeocodingService geocodingService = new GoogleGeocodingServiceImpl();
        
        // Read File Line By Line
        BufferedReader in = new BufferedReader(new FileReader(Files.CODES_POSTAUX_FILENAME));
        String line;
        while ((line = in.readLine()) != null) {
            String cp = line.substring(0, 5);
            String city = line.substring(6);
            if (city.toUpperCase().equals(city)) {
                try {
                    Coordinates geocode = geocodingService.geocode(city + " " + cp + ", France");
                } catch (GeocodingServiceException e) {
                    System.out.println(e.getMessage());
                    out.write(line);
                }
                out.write(cp + "-" + city);
            } else {
                out.write(line);
            }
        }
        out.close();
        
    }
}
