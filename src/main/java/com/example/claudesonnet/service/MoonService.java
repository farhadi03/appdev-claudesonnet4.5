package com.example.claudesonnet.service;

import com.example.claudesonnet.entity.Moon;
import com.example.claudesonnet.repository.MoonRepository;
import com.example.claudesonnet.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MoonService {
    
    private final MoonRepository moonRepository;
    private final PlanetRepository planetRepository;
    
    @Autowired
    public MoonService(MoonRepository moonRepository, PlanetRepository planetRepository) {
        this.moonRepository = moonRepository;
        this.planetRepository = planetRepository;
    }
    
    public List<Moon> getAllMoons() {
        return moonRepository.findAll();
    }
    
    public Optional<Moon> getMoonById(Long id) {
        return moonRepository.findById(id);
    }
    
    public List<Moon> getMoonsByPlanetId(Long planetId) {
        return moonRepository.findByPlanetId(planetId);
    }
    
    public Optional<Moon> createMoon(Long planetId, Moon moon) {
        return planetRepository.findById(planetId)
                .map(planet -> {
                    moon.setPlanet(planet);
                    return moonRepository.save(moon);
                });
    }
    
    public Optional<Moon> updateMoon(Long id, Moon moonDetails) {
        return moonRepository.findById(id)
                .map(moon -> {
                    moon.setName(moonDetails.getName());
                    moon.setDiameter(moonDetails.getDiameter());
                    moon.setOrbitalPeriod(moonDetails.getOrbitalPeriod());
                    return moonRepository.save(moon);
                });
    }
    
    public boolean deleteMoon(Long id) {
        return moonRepository.findById(id)
                .map(moon -> {
                    moonRepository.delete(moon);
                    return true;
                })
                .orElse(false);
    }
}

