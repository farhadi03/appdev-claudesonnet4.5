package com.example.claudesonnet.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "planets")
public class Planet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(nullable = false)
    private Double diameter; // in kilometers
    
    @Column(nullable = false)
    private Double distanceFromSun; // in million kilometers
    
    @OneToMany(mappedBy = "planet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Moon> moons = new ArrayList<>();
    
    // Constructors
    public Planet() {
    }
    
    public Planet(String name, Double diameter, Double distanceFromSun) {
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
    
    public List<Moon> getMoons() {
        return moons;
    }
    
    public void setMoons(List<Moon> moons) {
        this.moons = moons;
    }
    
    // Helper methods for bidirectional relationship
    public void addMoon(Moon moon) {
        moons.add(moon);
        moon.setPlanet(this);
    }
    
    public void removeMoon(Moon moon) {
        moons.remove(moon);
        moon.setPlanet(null);
    }
}

