package com.myapp.p_23_maplocator.Repository;

import com.myapp.p_23_maplocator.Model.District;
import com.myapp.p_23_maplocator.Model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

    List<District> findByState(State state);

    District findByDistrictName(String districtName);

    @Query(value = "SELECT ST_AsGeoJSON(geometry) FROM districts WHERE district_name = :districtName", nativeQuery = true)
    String getGeometryByDistrictName(@Param("districtName") String districtName);

    @Query(value = "SELECT ST_AsGeoJSON(geometry) AS geojson FROM districts WHERE state_id = :stateId", nativeQuery = true)
    List<String> getGeometriesByStateId(@Param("stateId") Long stateId);

    List<District> findByState_Id(Long id);
}
