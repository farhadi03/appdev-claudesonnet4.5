package com.example.claudesonnet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;

public class PlanetDTO {
    
    private Long id;
    
    @NotBlank(message = "Planet name is required")
    private String name;
    
    @NotNull(message = "Diameter is required")
    @Positive(message = "Diameter must be positive")
    private Double diameter;
    
    @NotNull(message = "Distance from sun is required")
    @Positive(message = "Distance from sun must be positive")
    private Double distanceFromSun;
    
    private List<MoonDTO> moons = new ArrayList<>();
    
    // Constructors
    public PlanetDTO() {
    }
    
    public PlanetDTO(Long id, String name, Double diameter, Double distanceFromSun) {
        this.id = id;
        this.name = name;
        this.diameter = diameter;
        this.distanceFromSun = distanceFromSun;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Double getDiameter() {
        return diameter;
    }
    
    public void setDiameter(Double diameter) {
        this.diameter = diameter;
    }
    
    public Double getDistanceFromSun() {
        return distanceFromSun;
    }
    
    public void setDistanceFromSun(Double distanceFromSun) {
        this.distanceFromSun = distanceFromSun;
    }
    
    public List<MoonDTO> getMoons() {
        return moons;
    }
    
    public void setMoons(List<MoonDTO> moons) {
        this.moons = moons;
    }
}

