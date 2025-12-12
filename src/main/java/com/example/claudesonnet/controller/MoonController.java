package com.example.claudesonnet.controller;

import com.example.claudesonnet.dto.MoonDTO;
import com.example.claudesonnet.service.MoonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moons")
public class MoonController {
    
    private final MoonService moonService;
    
    @Autowired
    public MoonController(MoonService moonService) {
        this.moonService = moonService;
    }
    
    @GetMapping
    public ResponseEntity<List<MoonDTO>> getAllMoons() {
        List<MoonDTO> moons = moonService.getAllMoons();
        return ResponseEntity.ok(moons);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MoonDTO> getMoonById(@PathVariable Long id) {
        MoonDTO moon = moonService.getMoonById(id);
        return ResponseEntity.ok(moon);
    }
    
    @GetMapping("/planet/{planetId}")
    public ResponseEntity<List<MoonDTO>> getMoonsByPlanetId(@PathVariable Long planetId) {
        List<MoonDTO> moons = moonService.getMoonsByPlanetId(planetId);
        return ResponseEntity.ok(moons);
    }
    
    @PostMapping("/planet/{planetId}")
    public ResponseEntity<MoonDTO> createMoon(@PathVariable Long planetId, @Valid @RequestBody MoonDTO moonDTO) {
        MoonDTO createdMoon = moonService.createMoon(planetId, moonDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMoon);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MoonDTO> updateMoon(@PathVariable Long id, @Valid @RequestBody MoonDTO moonDTO) {
        MoonDTO updatedMoon = moonService.updateMoon(id, moonDTO);
        return ResponseEntity.ok(updatedMoon);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMoon(@PathVariable Long id) {
        moonService.deleteMoon(id);
        return ResponseEntity.noContent().build();
    }
}

