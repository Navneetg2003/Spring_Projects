package com.myapp.p_23_maplocator.Repository;

import com.myapp.p_23_maplocator.Model.Country;
import com.myapp.p_23_maplocator.Model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    Optional<State> findByStateName(String stateName);

    List<State> findByCountry(Country country);

    @Query(value = """
        SELECT ST_AsGeoJSON(geometry) AS geojson
        FROM states
        WHERE state_name = :stateName
    """, nativeQuery = true)
    Optional<String> getStateGeoJSON(@Param("stateName") String stateName);

    @Query(value = """
        SELECT state_name
        FROM states
        WHERE country_id = :countryId
    """, nativeQuery = true)
    List<String> getStateNamesByCountryId(@Param("countryId") Long countryId);

    @Query(value = """
        SELECT ST_AsText(geometry) AS wkt
        FROM states
        WHERE state_name = :stateName
    """, nativeQuery = true)
    Optional<String> getStateGeometryAsWKT(@Param("stateName") String stateName);
}
