package com.myapp.p_23_maplocator.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.locationtech.jts.geom.Geometry;

import java.util.List;

@Entity
@Table(name = "states")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "geojson_id", unique = true, nullable = false)
    private Integer geoJsonId;

    @Column(name = "state_name", nullable = false)
    private String stateName;

    @Column(name = "country_name", nullable = false)
    private String countryName;

    @Column(name = "iso_code", nullable = false)
    private String isoCode;

    @Column(name = "nl_name", nullable = true)
    private String nlName;

    @Column(name = "var_name", nullable = true)
    private String varName;

    @Column(name = "type", nullable = true)
    private String type;

    @Column(name = "eng_type", nullable = true)
    private String engType;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    @JsonBackReference
    private Country country;

    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<District> districts;

    @Column(columnDefinition = "geometry(MultiPolygon, 4326)", nullable = false)
    @JdbcTypeCode(SqlTypes.GEOMETRY)
    private Geometry geometry;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGeoJsonId() {
        return geoJsonId;
    }

    public void setGeoJsonId(Integer geoJsonId) {
        this.geoJsonId = geoJsonId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getNlName() {
        return nlName;
    }

    public void setNlName(String nlName) {
        this.nlName = nlName;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEngType() {
        return engType;
    }

    public void setEngType(String engType) {
        this.engType = engType;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }
}
