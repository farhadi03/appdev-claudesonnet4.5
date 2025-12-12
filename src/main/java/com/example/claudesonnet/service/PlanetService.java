package com.example.claudesonnet.service;

import com.example.claudesonnet.dto.PlanetDTO;
import com.example.claudesonnet.entity.Planet;
import com.example.claudesonnet.exception.ResourceNotFoundException;
import com.example.claudesonnet.mapper.PlanetMapper;
import com.example.claudesonnet.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    
    public PlanetDTO getPlanetById(Long id) {
        Planet planet = planetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Planet", id));
        return planetMapper.toDTO(planet);
    }
    
    public PlanetDTO getPlanetByName(String name) {
        Planet planet = planetRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Planet", "name", name));
        return planetMapper.toDTO(planet);
    }
    
    public PlanetDTO createPlanet(PlanetDTO planetDTO) {
        Planet planet = planetMapper.toEntity(planetDTO);
        Planet savedPlanet = planetRepository.save(planet);
        return planetMapper.toDTO(savedPlanet);
    }
    
    public PlanetDTO updatePlanet(Long id, PlanetDTO planetDTO) {
        Planet planet = planetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Planet", id));
        
        planetMapper.updateEntityFromDTO(planetDTO, planet);
        Planet updatedPlanet = planetRepository.save(planet);
        return planetMapper.toDTO(updatedPlanet);
    }
    
    public void deletePlanet(Long id) {
        Planet planet = planetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Planet", id));
        planetRepository.delete(planet);
    }
}

