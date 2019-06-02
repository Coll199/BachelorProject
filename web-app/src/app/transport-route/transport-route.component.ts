import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { TransportRoute} from '../models/TransportRoute';


@Component({
  selector: 'app-transport-route',
  templateUrl: './transport-route.component.html',
  styleUrls: ['./transport-route.component.css']
})
export class TransportRouteComponent implements OnInit {
  transportRoute: TransportRoute;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      console.log(params.get('routeId'));
      this.transportRoute = TransportRoute[+params.get('routeId')];
    });
  }
}
