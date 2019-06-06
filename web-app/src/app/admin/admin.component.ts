import { Component, OnInit } from '@angular/core';
import {UserService} from '../services/user.service';
import {User} from '../models/User';
import {first, map} from 'rxjs/operators';
import {TransportRouteService} from '../services/transport-route.service';
import {TransportRoute} from '../models/TransportRoute';
import {VehicleService} from '../services/vehicle.service';
import {Vehicle} from '../models/Vehicle';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  users: User[] = [];
  tRoutes: TransportRoute[] = [];
  vehicles: Vehicle[] = [];

  constructor(private userService: UserService,
              private transportRouteService: TransportRouteService,
              private vehicleService: VehicleService) {}

  ngOnInit() {
    this.userService.getAll().pipe(first()).subscribe(users => {
      this.users = users;
    });
    this.transportRouteService.getAll().pipe(first()).subscribe(routes => {
      this.tRoutes = routes;
    });
    this.vehicleService.getAll().pipe(first()).subscribe(vehicles => {
      this.vehicles = vehicles;
    });
  }
  onDeleteRoute(tRoute: TransportRoute) {
    this.tRoutes = this.tRoutes.filter(r => r !== tRoute);
    this.transportRouteService.delete(tRoute.id.toString()).subscribe(resp => {
      console.log(resp);
    });
  }

  onDeleteVehicle(vehicle: Vehicle) {
    this.vehicles = this.vehicles.filter(v => v !== vehicle);
    this.vehicleService.delete(vehicle.id.toString()).subscribe(resp => {
      console.log(resp);
    });
  }

  onDeleteUser(user: User) {
    this.users = this.users.filter(u => u !== user);
    this.userService.delete(user.id).subscribe(resp => {
      console.log(resp);
    });
  }

}
