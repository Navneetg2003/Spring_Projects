package com.myapp.p_23_maplocator.Controller;

import com.myapp.p_23_maplocator.Model.District;
import com.myapp.p_23_maplocator.Model.State;
import com.myapp.p_23_maplocator.Service.DistrictService;
import com.myapp.p_23_maplocator.Service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/districts")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @Autowired
    private StateService stateService;

    @GetMapping
    public List<District> getAllDistricts() {
        return districtService.getAllDistricts();
    }

    @GetMapping("/statename/{stateName}")
    public ResponseEntity<List<String>> getDistrictsByStateName(@PathVariable String stateName) {
        try {
            Optional<State> state = stateService.getStateByName(stateName);
            if (state.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            List<District> districts = districtService.getDistrictsByStateId(state.get().getId());
            if (districts.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            List<String> districtNames = districts.stream()
                    .map(District::getDistrictName)
                    .collect(Collectors.toList());

            System.out.println(districtNames);

            return ResponseEntity.ok(districtNames);
        } catch (Exception e) {
            e.printStackTrace(); // Log the error for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{districtName}/geometry")
    public String getGeometryByDistrictName(@PathVariable String districtName) {
        return districtService.getGeometryByDistrictName(districtName);
    }

    @GetMapping("/state/{stateId}/geometries")
    public List<String> getGeometriesByStateId(@PathVariable Long stateId) {
        return districtService.getGeometriesByStateId(stateId);
    }
}
