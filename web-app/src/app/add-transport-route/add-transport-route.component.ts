import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {TransportRoute} from '../models/TransportRoute';
import {TransportRouteService} from '../services/transport-route.service';


@Component({
  selector: 'app-add-transport-route',
  templateUrl: './add-transport-route.component.html',
  styleUrls: ['./add-transport-route.component.css']
})
export class AddTransportRouteComponent implements OnInit {
  addRouteForm: FormGroup;
  tRoute: TransportRoute = new TransportRoute();
  isCreated = false;

  constructor(private formBuilder: FormBuilder,
              private transportRouteService: TransportRouteService) { }

  ngOnInit() {
    this.addRouteForm = this.formBuilder.group({
      routeName: [''],
      stations: [''],
    });
  }

  get f() { return this.addRouteForm.controls; }

  onSubmit() {
    this.tRoute.routeName = this.addRouteForm.value.routeName;
    this.tRoute.stations = this.addRouteForm.value.stations.split(';');
    this.tRoute.stations = this.tRoute.stations.map(s => s.trim()); // trim whitespace
    this.tRoute.stations = this.tRoute.stations.filter( e => e); // remove empty string
    this.transportRouteService.add(this.tRoute).subscribe(resp => {
      console.log(resp);
      this.isCreated = true;
      setTimeout(() => {
        this.isCreated = false;
      }, 2000);
    });
  }

}
