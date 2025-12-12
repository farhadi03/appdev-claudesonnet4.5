package com.example.claudesonnet.mapper;

import com.example.claudesonnet.dto.PlanetDTO;
import com.example.claudesonnet.entity.Planet;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PlanetMapper {
    
    private final MoonMapper moonMapper;
    
    public PlanetMapper(MoonMapper moonMapper) {
        this.moonMapper = moonMapper;
    }
    
    public PlanetDTO toDTO(Planet planet) {
        if (planet == null) {
            return null;
        }
        
        PlanetDTO dto = new PlanetDTO();
        dto.setId(planet.getId());
        dto.setName(planet.getName());
        dto.setDiameter(planet.getDiameter());
        dto.setDistanceFromSun(planet.getDistanceFromSun());
        
        // Convert moons to DTOs (without circular reference)
        if (planet.getMoons() != null) {
            dto.setMoons(planet.getMoons().stream()
                    .map(moonMapper::toSimpleDTO)
                    .collect(Collectors.toList()));
        }
        
        return dto;
    }
    
    public Planet toEntity(PlanetDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Planet planet = new Planet();
        planet.setId(dto.getId());
        planet.setName(dto.getName());
        planet.setDiameter(dto.getDiameter());
        planet.setDistanceFromSun(dto.getDistanceFromSun());
        
        return planet;
    }
    
    public void updateEntityFromDTO(PlanetDTO dto, Planet planet) {
        if (dto == null || planet == null) {
            return;
        }
        
        planet.setName(dto.getName());
        planet.setDiameter(dto.getDiameter());
        planet.setDistanceFromSun(dto.getDistanceFromSun());
    }
}

