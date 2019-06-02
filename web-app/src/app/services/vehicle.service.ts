import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Vehicle} from '../models/Vehicle';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<Vehicle[]>(`$localhost:4200/vehicles`);
  }
}
