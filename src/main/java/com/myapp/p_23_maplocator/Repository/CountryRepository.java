package com.myapp.p_23_maplocator.Repository;

import com.myapp.p_23_maplocator.Model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findByCountryName(String countryName);

    @Query(value = """
        SELECT ST_AsGeoJSON(geometry) AS geojson
        FROM countries
        WHERE country_name = :countryName
    """, nativeQuery = true)
    Optional<Map<String, Object>> getCountryGeoJSON(@Param("countryName") String countryName);

    @Query(value = "SELECT country_name FROM countries", nativeQuery = true)
    List<String> getAllCountryNames();
}
