package com.transport.smart.controller;

import com.transport.smart.message.GenericMessage;
import com.transport.smart.model.Vehicle;
import com.transport.smart.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/vehicle/")
public class VehicleController {

    @Autowired
    VehicleRepository vehicleRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> getVehicle(@PathVariable("id") Long id){
        if(!vehicleRepository.existsById(id)){
            ResponseEntity.status(404).body(new GenericMessage("Route not found"));
        }
        return ResponseEntity.ok().body(vehicleRepository.findById(id));
    }

    @GetMapping("/all")
    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }
}
