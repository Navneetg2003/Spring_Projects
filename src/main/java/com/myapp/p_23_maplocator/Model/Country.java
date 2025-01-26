package com.myapp.p_23_maplocator.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.locationtech.jts.geom.Geometry;

import java.util.List;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "iso_a3")
    private String isoA3;

    @Column(name = "iso_a2")
    private String isoA2;

    @Column(columnDefinition = "geometry(MultiPolygon, 4326)", nullable = false)
    @JdbcTypeCode(SqlTypes.GEOMETRY)
    private Geometry geometry;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<State> states;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getIsoA3() {
        return isoA3;
    }

    public void setIsoA3(String isoA3) {
        this.isoA3 = isoA3;
    }

    public String getIsoA2() {
        return isoA2;
    }

    public void setIsoA2(String isoA2) {
        this.isoA2 = isoA2;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }
}
