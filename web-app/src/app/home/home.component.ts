import { Component, OnInit } from '@angular/core';
import {TransportRouteService} from '../services/transport-route.service';
import {TransportRoute} from '../models/TransportRoute';
import {first} from 'rxjs/operators';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  tRoutes: TransportRoute[] = [];

  constructor(private transportRouteService: TransportRouteService) { }

  ngOnInit() {
    this.transportRouteService.getAll().pipe(first()).subscribe(routes => {
      this.tRoutes = routes;
    });
  }

}
