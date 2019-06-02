import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {TransportRoute} from '../models/TransportRoute';

@Injectable({
  providedIn: 'root'
})
export class TransportRouteService {

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<TransportRoute[]>(`$localhost:4200/routes`);
  }
}
