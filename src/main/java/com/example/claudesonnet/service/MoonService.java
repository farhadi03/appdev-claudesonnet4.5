package com.example.claudesonnet.service;

import com.example.claudesonnet.dto.MoonDTO;
import com.example.claudesonnet.entity.Moon;
import com.example.claudesonnet.mapper.MoonMapper;
import com.example.claudesonnet.repository.MoonRepository;
import com.example.claudesonnet.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
    
    public Optional<MoonDTO> getMoonById(Long id) {
        return moonRepository.findById(id)
                .map(moonMapper::toDTO);
    }
    
    public List<MoonDTO> getMoonsByPlanetId(Long planetId) {
        return moonRepository.findByPlanetId(planetId).stream()
                .map(moonMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    public Optional<MoonDTO> createMoon(Long planetId, MoonDTO moonDTO) {
        return planetRepository.findById(planetId)
                .map(planet -> {
                    Moon moon = moonMapper.toEntity(moonDTO);
                    moon.setPlanet(planet);
                    Moon savedMoon = moonRepository.save(moon);
                    return moonMapper.toDTO(savedMoon);
                });
    }
    
    public Optional<MoonDTO> updateMoon(Long id, MoonDTO moonDTO) {
        return moonRepository.findById(id)
                .map(moon -> {
                    moonMapper.updateEntityFromDTO(moonDTO, moon);
                    Moon updatedMoon = moonRepository.save(moon);
                    return moonMapper.toDTO(updatedMoon);
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

