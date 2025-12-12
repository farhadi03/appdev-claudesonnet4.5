package com.example.claudesonnet.service;

import com.example.claudesonnet.dto.PlanetDTO;
import com.example.claudesonnet.entity.Planet;
import com.example.claudesonnet.mapper.PlanetMapper;
import com.example.claudesonnet.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlanetService {
    
    private final PlanetRepository planetRepository;
    private final PlanetMapper planetMapper;
    
    @Autowired
    public PlanetService(PlanetRepository planetRepository, PlanetMapper planetMapper) {
        this.planetRepository = planetRepository;
        this.planetMapper = planetMapper;
    }
    
    public List<PlanetDTO> getAllPlanets() {
        return planetRepository.findAll().stream()
                .map(planetMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    public Optional<PlanetDTO> getPlanetById(Long id) {
        return planetRepository.findById(id)
                .map(planetMapper::toDTO);
    }
    
    public Optional<PlanetDTO> getPlanetByName(String name) {
        return planetRepository.findByName(name)
                .map(planetMapper::toDTO);
    }
    
    public PlanetDTO createPlanet(PlanetDTO planetDTO) {
        Planet planet = planetMapper.toEntity(planetDTO);
        Planet savedPlanet = planetRepository.save(planet);
        return planetMapper.toDTO(savedPlanet);
    }
    
    public Optional<PlanetDTO> updatePlanet(Long id, PlanetDTO planetDTO) {
        return planetRepository.findById(id)
                .map(planet -> {
                    planetMapper.updateEntityFromDTO(planetDTO, planet);
                    Planet updatedPlanet = planetRepository.save(planet);
                    return planetMapper.toDTO(updatedPlanet);
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

