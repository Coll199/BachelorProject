import { Component, OnInit } from '@angular/core';
import {UserService} from '../services/user.service';
import {User} from '../models/User';
import {first} from 'rxjs/operators';
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

}
