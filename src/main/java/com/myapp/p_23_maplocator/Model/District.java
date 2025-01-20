package com.myapp.p_23_maplocator.Model;

import jakarta.persistence.*;
import org.locationtech.jts.geom.Geometry;

@Entity
@Table(name = "districts")
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "district_name", nullable = false)
    private String districtName;

    @Column(name = "geojson_id", unique = true, nullable = false)
    private Integer geoJsonId;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @Column(name = "shape_length", nullable = true)
    private Double shapeLength;

    @Column(name = "shape_area", nullable = true)
    private Double shapeArea;

    @Column(columnDefinition = "geometry(MultiPolygon, 4326)", nullable = false)
    private Geometry geometry;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Integer getGeoJsonId() {
        return geoJsonId;
    }

    public void setGeoJsonId(Integer geoJsonId) {
        this.geoJsonId = geoJsonId;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Double getShapeLength() {
        return shapeLength;
    }

    public void setShapeLength(Double shapeLength) {
        this.shapeLength = shapeLength;
    }

    public Double getShapeArea() {
        return shapeArea;
    }

    public void setShapeArea(Double shapeArea) {
        this.shapeArea = shapeArea;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
