package com.example.claudesonnet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "moons")
public class Moon {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private Double diameter; // in kilometers
    
    @Column(nullable = false)
    private Double orbitalPeriod; // in days
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planet_id", nullable = false)
    @JsonIgnore
    private Planet planet;
    
    // Constructors
    public Moon() {
    }
    
    public Moon(String name, Double diameter, Double orbitalPeriod) {
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
    
    public Planet getPlanet() {
        return planet;
    }
    
    public void setPlanet(Planet planet) {
        this.planet = planet;
    }
}

