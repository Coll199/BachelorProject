import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {TransportRoute} from '../models/TransportRoute';
import {AuthenticationService} from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class TransportRouteService {

  constructor(private http: HttpClient, private authenticationService: AuthenticationService) { }

  getAll() {
    return this.http.get<TransportRoute[]>(`http://localhost:8080/route/all`);
  }

  get(id: string) {
    return this.http.get<TransportRoute>(`http://localhost:8080/route/` + id);
  }

  add(tRoute: TransportRoute) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': 'Bearer ' + this.authenticationService.currentUserValue.token,
      })
    };
    return this.http.post<TransportRoute>('http://localhost:8080/route/add', tRoute, httpOptions);
  }

  delete(id: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': 'Bearer ' + this.authenticationService.currentUserValue.token,
      })
    };
    return this.http.delete<TransportRoute>(`http://localhost:8080/route/` + id, httpOptions);
  }
}
