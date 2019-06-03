package com.transport.smart.controller;

import com.transport.smart.message.GenericMessage;
import com.transport.smart.model.Route;
import com.transport.smart.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/route/")
public class RouteController {
    @Autowired
    RouteRepository routeRepository;

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
}
