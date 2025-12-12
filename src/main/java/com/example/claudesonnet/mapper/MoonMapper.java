package com.example.claudesonnet.mapper;

import com.example.claudesonnet.dto.MoonDTO;
import com.example.claudesonnet.entity.Moon;
import org.springframework.stereotype.Component;

@Component
public class MoonMapper {
    
    public MoonDTO toDTO(Moon moon) {
        if (moon == null) {
            return null;
        }
        
        MoonDTO dto = new MoonDTO();
        dto.setId(moon.getId());
        dto.setName(moon.getName());
        dto.setDiameter(moon.getDiameter());
        dto.setOrbitalPeriod(moon.getOrbitalPeriod());
        
        // Include planet information
        if (moon.getPlanet() != null) {
            dto.setPlanetId(moon.getPlanet().getId());
            dto.setPlanetName(moon.getPlanet().getName());
        }
        
        return dto;
    }
    
    public MoonDTO toSimpleDTO(Moon moon) {
        if (moon == null) {
            return null;
        }
        
        MoonDTO dto = new MoonDTO();
        dto.setId(moon.getId());
        dto.setName(moon.getName());
        dto.setDiameter(moon.getDiameter());
        dto.setOrbitalPeriod(moon.getOrbitalPeriod());
        
        // Don't include planet information to avoid circular reference
        return dto;
    }
    
    public Moon toEntity(MoonDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Moon moon = new Moon();
        moon.setId(dto.getId());
        moon.setName(dto.getName());
        moon.setDiameter(dto.getDiameter());
        moon.setOrbitalPeriod(dto.getOrbitalPeriod());
        
        return moon;
    }
    
    public void updateEntityFromDTO(MoonDTO dto, Moon moon) {
        if (dto == null || moon == null) {
            return;
        }
        
        moon.setName(dto.getName());
        moon.setDiameter(dto.getDiameter());
        moon.setOrbitalPeriod(dto.getOrbitalPeriod());
    }
}

