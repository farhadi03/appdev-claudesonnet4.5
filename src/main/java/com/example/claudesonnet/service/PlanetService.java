package com.example.claudesonnet.service;

import com.example.claudesonnet.entity.Planet;
import com.example.claudesonnet.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlanetService {
    
    private final PlanetRepository planetRepository;
    
    @Autowired
    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }
    
    public List<Planet> getAllPlanets() {
        return planetRepository.findAll();
    }
    
    public Optional<Planet> getPlanetById(Long id) {
        return planetRepository.findById(id);
    }
    
    public Optional<Planet> getPlanetByName(String name) {
        return planetRepository.findByName(name);
    }
    
    public Planet createPlanet(Planet planet) {
        return planetRepository.save(planet);
    }
    
    public Optional<Planet> updatePlanet(Long id, Planet planetDetails) {
        return planetRepository.findById(id)
                .map(planet -> {
                    planet.setName(planetDetails.getName());
                    planet.setDiameter(planetDetails.getDiameter());
                    planet.setDistanceFromSun(planetDetails.getDistanceFromSun());
                    return planetRepository.save(planet);
                });
    }
    
    public boolean deletePlanet(Long id) {
        return planetRepository.findById(id)
                .map(planet -> {
                    planetRepository.delete(planet);
                    return true;
                })
                .orElse(false);
    }
}

