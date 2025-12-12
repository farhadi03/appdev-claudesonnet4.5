package com.example.claudesonnet.config;

import com.example.claudesonnet.entity.Moon;
import com.example.claudesonnet.entity.Planet;
import com.example.claudesonnet.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    private final PlanetRepository planetRepository;
    
    @Autowired
    public DataInitializer(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }
    
    @Override
    public void run(String... args) throws Exception {
        // Create Earth with its moon
        Planet earth = new Planet("Earth", 12742.0, 149.6);
        Moon earthMoon = new Moon("Moon", 3474.8, 27.3);
        earth.addMoon(earthMoon);
        planetRepository.save(earth);
        
        // Create Mars with its moons
        Planet mars = new Planet("Mars", 6779.0, 227.9);
        Moon phobos = new Moon("Phobos", 22.4, 0.3);
        Moon deimos = new Moon("Deimos", 12.4, 1.3);
        mars.addMoon(phobos);
        mars.addMoon(deimos);
        planetRepository.save(mars);
        
        // Create Jupiter with some of its major moons
        Planet jupiter = new Planet("Jupiter", 139820.0, 778.5);
        Moon io = new Moon("Io", 3643.2, 1.8);
        Moon europa = new Moon("Europa", 3121.6, 3.5);
        Moon ganymede = new Moon("Ganymede", 5268.2, 7.2);
        Moon callisto = new Moon("Callisto", 4820.6, 16.7);
        jupiter.addMoon(io);
        jupiter.addMoon(europa);
        jupiter.addMoon(ganymede);
        jupiter.addMoon(callisto);
        planetRepository.save(jupiter);
        
        // Create Saturn with some of its major moons
        Planet saturn = new Planet("Saturn", 116460.0, 1434.0);
        Moon titan = new Moon("Titan", 5149.5, 15.9);
        Moon rhea = new Moon("Rhea", 1527.0, 4.5);
        Moon iapetus = new Moon("Iapetus", 1469.0, 79.3);
        saturn.addMoon(titan);
        saturn.addMoon(rhea);
        saturn.addMoon(iapetus);
        planetRepository.save(saturn);
        
        // Create Neptune with its major moon
        Planet neptune = new Planet("Neptune", 49244.0, 4495.1);
        Moon triton = new Moon("Triton", 2706.8, 5.9);
        neptune.addMoon(triton);
        planetRepository.save(neptune);
        
        System.out.println("Sample planetary data initialized successfully!");
    }
}

