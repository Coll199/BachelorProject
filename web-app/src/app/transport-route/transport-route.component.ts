import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { TransportRoute} from '../models/TransportRoute';
import {TransportRouteService} from '../services/transport-route.service';
import {first} from 'rxjs/operators';


@Component({
  selector: 'app-transport-route',
  templateUrl: './transport-route.component.html',
  styleUrls: ['./transport-route.component.css']
})
export class TransportRouteComponent implements OnInit {
  transportRoute: TransportRoute;

  constructor(private route: ActivatedRoute, private transportRouteService: TransportRouteService) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      console.log(params.get('routeId'));
      this.transportRouteService.get(params.get('routeId')).pipe(first()).subscribe(route => {
        this.transportRoute = route;
      });
    });
  }
}
