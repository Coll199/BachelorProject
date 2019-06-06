import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Vehicle} from '../models/Vehicle';
import { AuthenticationService} from '../services/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  constructor(private http: HttpClient,
              private authenticationService: AuthenticationService) { }

  add(veh: Vehicle) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        Authorization: 'Bearer ' + this.authenticationService.currentUserValue.token,
      })
    };
    return this.http.post<Vehicle>('http://localhost:8080/vehicle/add', veh, httpOptions);
  }

  getAll() {
    return this.http.get<Vehicle[]>(`http://localhost:8080/vehicle/all`);
  }

  get(id: string) {
    return this.http.get<Vehicle>(`http://localhost:8080/vehicle/` + id);
  }

  delete(id: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        Authorization: 'Bearer ' + this.authenticationService.currentUserValue.token,
      })
    };
    return this.http.delete<Vehicle>(`http://localhost:8080/vehicle/` + id, httpOptions);
  }
}
