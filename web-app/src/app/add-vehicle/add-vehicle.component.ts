import { Component, OnInit } from '@angular/core';
import {Vehicle} from '../models/Vehicle';
import {VehicleService} from '../services/vehicle.service';
import {RaspberryPi} from '../models/RaspberryPi';
import {TransportRoute} from '../models/TransportRoute';

@Component({
  selector: 'app-add-vehicle',
  templateUrl: './add-vehicle.component.html',
  styleUrls: ['./add-vehicle.component.css']
})
export class AddVehicleComponent implements OnInit {
  veh: Vehicle;
  isCreated = false;

  constructor(private vehicleService: VehicleService) { }

  ngOnInit() {
    this.veh = new Vehicle();
    this.veh.pi = new RaspberryPi();
    this.veh.route = new TransportRoute();
  }

  onSubmit() {
    this.vehicleService.add(this.veh).subscribe(resp => {
      console.log(resp);
      this.isCreated = true;
      setTimeout(() => {
        this.isCreated = false;
      }, 2000);
    });
  }

}
