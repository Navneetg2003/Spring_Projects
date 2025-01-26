package com.myapp.p_23_maplocator.Controller;

import com.myapp.p_23_maplocator.Model.Country;
import com.myapp.p_23_maplocator.Service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = countryService.getAllCountries();
        if (countries.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Country> getCountryByName(@PathVariable String name) {
        return countryService.getCountryByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/geojson/{countryName}")
    public ResponseEntity<?> getCountryGeoJSON(@PathVariable String countryName) {
        try {
            String geojson = countryService.getCountryGeometryAsGeoJSON(countryName);

            if (geojson == null || geojson.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("ERROR");
            }
            return ResponseEntity.ok(geojson);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("ERROR");
        }
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> getAllCountryNames() {
        List<String> countryNames = countryService.getAllCountryNames();
        if (countryNames.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(countryNames);
    }
}
