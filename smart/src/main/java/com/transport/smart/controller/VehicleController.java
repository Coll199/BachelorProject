package com.transport.smart.controller;

import com.transport.smart.message.GenericMessage;
import com.transport.smart.model.RaspberryPi;
import com.transport.smart.model.Vehicle;
import com.transport.smart.repository.RaspberryPiRepository;
import com.transport.smart.repository.RouteRepository;
import com.transport.smart.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/vehicle/")
public class VehicleController {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    RaspberryPiRepository raspberryPiRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addVehicle(@RequestBody Vehicle vehicle){
        if(vehicleRepository.existsByName(vehicle.getName())){
            return ResponseEntity.badRequest().body(new GenericMessage("vehicle name already exists"));
        }
        Vehicle veh = new Vehicle();
        veh.setName(vehicle.getName());
        veh.setRoute(routeRepository.findByRouteName(vehicle.getRoute().getRouteName()));
        RaspberryPi pi = new RaspberryPi();
        pi.setIpAddress(vehicle.getPi().getIpAddress());
        pi.setPort(vehicle.getPi().getPort());
        veh.setPi(pi);
        vehicleRepository.save(veh);
        return ResponseEntity.ok().body(new GenericMessage("vehicle created"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVehicle(@PathVariable("id") Long id){
        if(!vehicleRepository.existsById(id)){
            ResponseEntity.status(404).body(new GenericMessage("Vehicle not found"));
        }
        return ResponseEntity.ok().body(vehicleRepository.findById(id));
    }

    @GetMapping("/all")
    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable("id") Long id){
        if(!vehicleRepository.existsById(id)){
            ResponseEntity.status(404).body(new GenericMessage("Vehicle not found"));
        }
        vehicleRepository.deleteById(id);
        return ResponseEntity.ok().body(new GenericMessage("Vehicle deleted"));
    }
}
