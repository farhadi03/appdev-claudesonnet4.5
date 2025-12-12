package com.example.claudesonnet.service;

import com.example.claudesonnet.dto.MoonDTO;
import com.example.claudesonnet.entity.Moon;
import com.example.claudesonnet.entity.Planet;
import com.example.claudesonnet.exception.ResourceNotFoundException;
import com.example.claudesonnet.mapper.MoonMapper;
import com.example.claudesonnet.repository.MoonRepository;
import com.example.claudesonnet.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MoonService {
    
    private final MoonRepository moonRepository;
    private final PlanetRepository planetRepository;
    private final MoonMapper moonMapper;
    
    @Autowired
    public MoonService(MoonRepository moonRepository, PlanetRepository planetRepository, MoonMapper moonMapper) {
        this.moonRepository = moonRepository;
        this.planetRepository = planetRepository;
        this.moonMapper = moonMapper;
    }
    
    public List<MoonDTO> getAllMoons() {
        return moonRepository.findAll().stream()
                .map(moonMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    public MoonDTO getMoonById(Long id) {
        Moon moon = moonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Moon", id));
        return moonMapper.toDTO(moon);
    }
    
    public List<MoonDTO> getMoonsByPlanetId(Long planetId) {
        // Verify planet exists
        planetRepository.findById(planetId)
                .orElseThrow(() -> new ResourceNotFoundException("Planet", planetId));
        
        return moonRepository.findByPlanetId(planetId).stream()
                .map(moonMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    public MoonDTO createMoon(Long planetId, MoonDTO moonDTO) {
        Planet planet = planetRepository.findById(planetId)
                .orElseThrow(() -> new ResourceNotFoundException("Planet", planetId));
        
        Moon moon = moonMapper.toEntity(moonDTO);
        moon.setPlanet(planet);
        Moon savedMoon = moonRepository.save(moon);
        return moonMapper.toDTO(savedMoon);
    }
    
    public MoonDTO updateMoon(Long id, MoonDTO moonDTO) {
        Moon moon = moonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Moon", id));
        
        moonMapper.updateEntityFromDTO(moonDTO, moon);
        Moon updatedMoon = moonRepository.save(moon);
        return moonMapper.toDTO(updatedMoon);
    }
    
    public void deleteMoon(Long id) {
        Moon moon = moonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Moon", id));
        moonRepository.delete(moon);
    }
}

