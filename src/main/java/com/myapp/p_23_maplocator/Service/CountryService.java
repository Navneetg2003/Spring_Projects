package com.myapp.p_23_maplocator.Service;

import com.myapp.p_23_maplocator.Model.Country;
import com.myapp.p_23_maplocator.Repository.CountryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    // Get all countries
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    // Save a new country
    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }

    // Find a country by its name
    public Optional<Country> getCountryByName(String countryName) {
        return countryRepository.findByCountryName(countryName);
    }

    // Get GeoJSON representation of a country's geometry
    public String getCountryGeometryAsGeoJSON(String countryName) {
        return countryRepository.getCountryGeoJSON(countryName)
                .map(result -> (String) result.get("geojson")) // Extract "geojson" field
                .orElseThrow(() -> new RuntimeException("GeoJSON data not found for country: " + countryName));
    }

    public List<String> getAllCountryNames() {
        return countryRepository.getAllCountryNames();
    }
}
