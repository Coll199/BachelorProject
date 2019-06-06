package com.transport.smart.controller;

import com.transport.smart.message.GenericMessage;
import com.transport.smart.model.Route;
import com.transport.smart.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/route/")
public class RouteController {
    @Autowired
    RouteRepository routeRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> registerUser(@RequestBody Route routeRequest){
        routeRepository.save(routeRequest);
        return ResponseEntity.ok().body(new GenericMessage("Route created successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoute(@PathVariable("id") Long id){
        if(!routeRepository.existsById(id)){
            ResponseEntity.status(404).body(new GenericMessage("Route not found"));
        }
        return ResponseEntity.ok().body(routeRepository.findById(id));
    }

    @GetMapping("/all")
    public List<Route> getAllRoutes(){
        return routeRepository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoute(@PathVariable("id") Long id){
        if(!routeRepository.existsById(id)){
            ResponseEntity.status(404).body(new GenericMessage("Route not found"));
        }
        routeRepository.deleteById(id);
        return ResponseEntity.ok().body(new GenericMessage("Route deleted"));
    }
}
