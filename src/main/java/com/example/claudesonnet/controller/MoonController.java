package com.example.claudesonnet.controller;

import com.example.claudesonnet.entity.Moon;
import com.example.claudesonnet.service.MoonService;
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
    public ResponseEntity<List<Moon>> getAllMoons() {
        List<Moon> moons = moonService.getAllMoons();
        return ResponseEntity.ok(moons);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Moon> getMoonById(@PathVariable Long id) {
        return moonService.getMoonById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/planet/{planetId}")
    public ResponseEntity<List<Moon>> getMoonsByPlanetId(@PathVariable Long planetId) {
        List<Moon> moons = moonService.getMoonsByPlanetId(planetId);
        return ResponseEntity.ok(moons);
    }
    
    @PostMapping("/planet/{planetId}")
    public ResponseEntity<Moon> createMoon(@PathVariable Long planetId, @RequestBody Moon moon) {
        return moonService.createMoon(planetId, moon)
                .map(createdMoon -> ResponseEntity.status(HttpStatus.CREATED).body(createdMoon))
                .orElse(ResponseEntity.badRequest().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Moon> updateMoon(@PathVariable Long id, @RequestBody Moon moon) {
        return moonService.updateMoon(id, moon)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMoon(@PathVariable Long id) {
        if (moonService.deleteMoon(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

