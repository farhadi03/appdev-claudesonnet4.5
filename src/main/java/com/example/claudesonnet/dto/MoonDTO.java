package com.example.claudesonnet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class MoonDTO {
    
    private Long id;
    
    @NotBlank(message = "Moon name is required")
    private String name;
    
    @NotNull(message = "Diameter is required")
    @Positive(message = "Diameter must be positive")
    private Double diameter;
    
    @NotNull(message = "Orbital period is required")
    @Positive(message = "Orbital period must be positive")
    private Double orbitalPeriod;
    
    private Long planetId;
    private String planetName;
    
    // Constructors
    public MoonDTO() {
    }
    
    public MoonDTO(Long id, String name, Double diameter, Double orbitalPeriod) {
        this.id = id;
        this.name = name;
        this.diameter = diameter;
        this.orbitalPeriod = orbitalPeriod;
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
    
    public Double getOrbitalPeriod() {
        return orbitalPeriod;
    }
    
    public void setOrbitalPeriod(Double orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }
    
    public Long getPlanetId() {
        return planetId;
    }
    
    public void setPlanetId(Long planetId) {
        this.planetId = planetId;
    }
    
    public String getPlanetName() {
        return planetName;
    }
    
    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }
}

