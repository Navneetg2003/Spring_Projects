package com.myapp.p_23_maplocator.Service;

import com.myapp.p_23_maplocator.Model.Country;
import com.myapp.p_23_maplocator.Model.State;
import com.myapp.p_23_maplocator.Repository.CountryRepository;
import com.myapp.p_23_maplocator.Repository.StateRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CountryRepository countryRepository;

    public List<State> getAllStates() {
        return stateRepository.findAll();
    }

    public List<State> getStatesByCountry(String countryName) {
        Country country = countryRepository.findByCountryName(countryName)
                .orElseThrow(() -> new RuntimeException("Country not found: " + countryName));
        return stateRepository.findByCountry(country);
    }

    public State saveState(State state) {
        return stateRepository.save(state);
    }

    public Optional<State> getStateById(Long id) {
        return stateRepository.findById(id);
    }

    public Optional<State> getStateByName(String stateName) {
        return stateRepository.findByStateName(stateName);
    }

    public String getStateGeometryAsGeoJSON(String stateName) {
        return stateRepository.getStateGeoJSON(stateName)
                .orElseThrow(() -> new RuntimeException("GeoJSON not found for state: " + stateName));
    }

}
