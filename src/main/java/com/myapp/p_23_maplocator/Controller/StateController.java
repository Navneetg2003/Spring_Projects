package com.myapp.p_23_maplocator.Controller;

import com.myapp.p_23_maplocator.Model.State;
import com.myapp.p_23_maplocator.Service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/states")
public class StateController {

    @Autowired
    private StateService stateService;

    @GetMapping
    public ResponseEntity<List<State>> getAllStates() {
        List<State> states = stateService.getAllStates();
        if (states.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(states);
    }

    @GetMapping("/byCountry/{countryName}")
    public ResponseEntity<List<String>> getStatesByCountry(@PathVariable String countryName) {
        try {
            List<State> states = stateService.getStatesByCountry(countryName);
            if (states.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            List<String> stateNames = states.stream()
                    .map(State::getStateName)
                    .collect(Collectors.toList());

//            System.out.println(stateNames);

            return ResponseEntity.ok(stateNames);

        } catch (Exception e) {
            e.printStackTrace(); // Log the error for debugging
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<State> saveState(@RequestBody State state) {
        try {
            State savedState = stateService.saveState(state);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedState);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/{stateName}")
    public ResponseEntity<State> getStateByName(@PathVariable String stateName) {
        return stateService.getStateByName(stateName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

//    @GetMapping("/{stateName}/geometry-wkt")
//    public ResponseEntity<String> getStateGeometryAsWKT(@PathVariable String stateName) {
//        try {
//            String geometryWKT = stateService.getStateGeometryAsWKT(stateName);
//            return ResponseEntity.ok(geometryWKT);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("{\"error\": \"Geometry not found for state: " + stateName + "\"}");
//        }
//    }

    @GetMapping("/{stateName}/geometry-geojson")
    public ResponseEntity<String> getStateGeometryAsGeoJSON(@PathVariable String stateName) {
        try {
            String geometryGeoJSON = stateService.getStateGeometryAsGeoJSON(stateName);
            return ResponseEntity.ok(geometryGeoJSON);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"GeoJSON not found for state: " + stateName + "\"}");
        }
    }
}
