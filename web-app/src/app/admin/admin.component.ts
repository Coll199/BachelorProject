import { Component, OnInit } from '@angular/core';
import {UserService} from '../services/user.service';
import {User} from '../models/User';
import {first} from 'rxjs/operators';
import {TransportRouteService} from '../services/transport-route.service';
import {TransportRoute} from '../models/TransportRoute';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  users: User[] = [];
  tRoutes: TransportRoute[] = [];
  constructor(private userService: UserService, private transportRouteService: TransportRouteService) {}

  ngOnInit() {
    this.userService.getAll().pipe(first()).subscribe(users => {
      this.users = users;
    });
    this.transportRouteService.getAll().pipe(first()).subscribe(routes => {
      this.tRoutes = routes;
    });
  }

}
