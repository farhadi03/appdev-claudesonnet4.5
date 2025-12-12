package com.example.claudesonnet.controller;

import com.example.claudesonnet.dto.PlanetDTO;
import com.example.claudesonnet.service.PlanetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planets")
public class PlanetController {
    
    private final PlanetService planetService;
    
    @Autowired
    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }
    
    @GetMapping
    public ResponseEntity<List<PlanetDTO>> getAllPlanets() {
        List<PlanetDTO> planets = planetService.getAllPlanets();
        return ResponseEntity.ok(planets);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PlanetDTO> getPlanetById(@PathVariable Long id) {
        return planetService.getPlanetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/search")
    public ResponseEntity<PlanetDTO> getPlanetByName(@RequestParam String name) {
        return planetService.getPlanetByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<PlanetDTO> createPlanet(@Valid @RequestBody PlanetDTO planetDTO) {
        PlanetDTO createdPlanet = planetService.createPlanet(planetDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlanet);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PlanetDTO> updatePlanet(@PathVariable Long id, @Valid @RequestBody PlanetDTO planetDTO) {
        return planetService.updatePlanet(id, planetDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanet(@PathVariable Long id) {
        if (planetService.deletePlanet(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

