import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { AuthenticationService} from '../services/authentication.service';
import {User} from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private authenticationService: AuthenticationService) {}

  getAll() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': 'Bearer ' + this.authenticationService.currentUserValue.token,
      })
    };
    return this.http.get<any>(`http://localhost:8080/user/all`, httpOptions);
  }

  getById(id: number) {
    return this.http.get<User>(`http://localhost:8080/user/${id}`);
  }

  register(user) {
    return this.http.post(`http://localhost:8080/user/register`, user);
  }

  delete(id: string) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': 'Bearer ' + this.authenticationService.currentUserValue.token,
      })
    };
    return this.http.delete(`http://localhost:8080/user/` + id, httpOptions);
  }
}
