import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SubscriptionRequest} from '../models/SubscriptionRequest';
import {AuthenticationService} from '../services/authentication.service';
import {Subscription} from '../models/Subscription';

@Component({
  selector: 'app-subscription',
  templateUrl: './subscription.component.html',
  styleUrls: ['./subscription.component.css']
})
export class SubscriptionComponent implements OnInit {
  days: number;
  subscriptionRequest = new SubscriptionRequest();
  subscription: Subscription;

  constructor(private http: HttpClient, private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.http.get<any>('http://localhost:8080/subscription/' + this.authenticationService.currentUserValue.id).subscribe(resp => {
      if (resp) {
        this.subscription  = new Subscription();
        this.subscription.expiresOn = resp.expiresOn;
        this.subscription.expiresOn = this.subscription.expiresOn.split('T')[0];
      }
      console.log(resp);
    }, err => {
      if (err.status === 400) {
        this.subscription = null;
      }
    });
  }

  onSubmit() {
    if (this.days) {
      this.subscriptionRequest.userId = this.authenticationService.currentUserValue.id;
      this.subscriptionRequest.days = this.days;
      this.http.post('http://localhost:8080/subscription/add', this.subscriptionRequest).subscribe(resp => {
        this.ngOnInit();
        console.log(resp);
      });
    }

  }

}
