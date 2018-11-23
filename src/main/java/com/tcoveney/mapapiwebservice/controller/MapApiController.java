package com.tcoveney.mapapiwebservice.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.errors.InvalidRequestException;
import com.google.maps.model.GeocodingResult;

@RestController
@RequestMapping("/mapapi")
public class MapApiController {
	@Value( "${apikey}" )
	private String apikey;

	@GetMapping("/geocode")
	public String geocode(@RequestParam String address) {
		GeoApiContext geoApiContext = new GeoApiContext.Builder().apiKey(apikey).build();
		GeocodingResult[] results = null;
		
		if ("" == address) {
			return "ERROR: 'address' request parameter does not have a value";
		}

		try {
			results = GeocodingApi.geocode(geoApiContext, address).await();
		} catch (ApiException ae) {
			// TODO: Log error
			return "ERROR: " + ae.getMessage();
		} catch (InterruptedException ie) {
			// TODO: Log error
			return "ERROR: " + ie.getMessage();
		} catch (IOException ioe) {
			// TODO: Log error
			return "ERROR: " + ioe.getMessage();
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		return gson.toJson(results[0]);
	}

}
