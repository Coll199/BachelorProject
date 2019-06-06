import {Component, OnDestroy, OnInit} from '@angular/core';
import {Vehicle} from '../models/Vehicle';
import { NavigationStart, ActivatedRoute, Router} from '@angular/router';
import {VehicleService} from '../services/vehicle.service';
import {first} from 'rxjs/operators';
import io from 'socket.io-client';

@Component({
  selector: 'app-vehicle',
  templateUrl: './vehicle.component.html',
  styleUrls: ['./vehicle.component.css']
})
export class VehicleComponent implements OnInit {
  vehicle: Vehicle;
  private streamLink: string;
  private socket: any;
  private gotOn = 0;
  private gotOff = 0;
  private validated = 0;

  constructor(private router: Router, private route: ActivatedRoute, private vehicleService: VehicleService) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.vehicleService.get(params.get('vehicleId')).pipe(first()).subscribe(veh => {
        this.vehicle = veh;
        this.streamLink = 'http://' + this.vehicle.pi.ipAddress + ':8080/stream/video.mjpeg';
        this.socket = io('http://' + this.vehicle.pi.ipAddress + ':' + this.vehicle.pi.port);
        this.socket.on('passengers', (pData) => {
          this.gotOn = pData.gotOn;
          this.gotOff = pData.gotOff;
          this.validated = pData.validated;
        });
      });
    });
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationStart) {
        this.socket.disconnect();
      }
    });
  }

}
