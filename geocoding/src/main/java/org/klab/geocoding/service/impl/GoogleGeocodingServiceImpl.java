package org.klab.geocoding.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.util.URIUtil;
import org.klab.geocoding.model.Coordinates;
import org.klab.geocoding.service.GeocodingService;
import org.klab.geocoding.service.GeocodingServiceException;

public class GoogleGeocodingServiceImpl implements GeocodingService {
    
    private static final String SERVICE_URL = "http://maps.google.com/maps/geo";
    
    @Override
    public Coordinates geocode(String address) throws GeocodingServiceException {
        
        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(SERVICE_URL);
        
        String queryString = buildGeocodeQueryString(address);
        method.setQueryString(queryString);
        
        try {
            client.executeMethod(method);
        } catch (HttpException e) {
            throw new GeocodingServiceException("HTTP error while sending query : " + queryString, e);
        } catch (IOException e) {
            throw new GeocodingServiceException("I/O error while sending query : " + queryString, e);
        }
        
        String response;
        try {
            response = inputStreamAsString(method.getResponseBodyAsStream());
        } catch (IOException e) {
            throw new GeocodingServiceException("Error while getting query response : " + queryString, e);
        }
        
        method.releaseConnection();
        
        // response format is : <HTTP status code>,<accuracy>,<latitude>,<longitude>
        String[] parts = response.split(",");
        int status = new Integer(parts[0]);
        
        validateResponse(address, status);
        
        float latitude = Float.valueOf(parts[2]).floatValue();
        float longitude = Float.valueOf(parts[3]).floatValue();
        
        return new Coordinates(latitude, longitude, 0);
    }
    
    private void validateResponse(String addresse, int code) throws GeocodingServiceException {
        GoogleStatusCode status = GoogleStatusCode.fromCode(code);
        if (!status.equals(GoogleStatusCode.G_GEO_SUCCESS)) {
            throw new GeocodingServiceException(status.getDescription() + " (address='" + addresse + "')");
        }
    }
    
    private String buildGeocodeQueryString(String address) throws GeocodingServiceException {
        StringBuffer buffer = new StringBuffer();
        buffer.append("q=");
        buffer.append(address);
        buffer.append("&sensor=false&output=csv");
        
        try {
            return URIUtil.encodeQuery(buffer.toString());
        } catch (URIException e) {
            throw new GeocodingServiceException("Error while encoding url query : " + buffer.toString(), e);
        }
    }
    
    public static String inputStreamAsString(InputStream stream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        StringBuilder sb = new StringBuilder();
        String line = null;
        
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        
        br.close();
        return sb.toString();
    }
}
