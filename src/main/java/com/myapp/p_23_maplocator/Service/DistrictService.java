package com.myapp.p_23_maplocator.Service;

import com.myapp.p_23_maplocator.Model.District;
import com.myapp.p_23_maplocator.Model.State;
import com.myapp.p_23_maplocator.Repository.DistrictRepository;
import com.myapp.p_23_maplocator.Repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private StateRepository stateRepository;

    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    public List<District> getDistrictsByStateId(Long stateId) {
        return districtRepository.findByState_Id(stateId); // Updated to use correct method
    }

    public String getGeometryByDistrictName(String districtName) {
        return districtRepository.getGeometryByDistrictName(districtName);
    }

    public List<String> getGeometriesByStateId(Long stateId) {
        return districtRepository.getGeometriesByStateId(stateId);
    }
}
