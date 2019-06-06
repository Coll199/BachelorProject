import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin.component';
import { RegisterComponent } from './register/register.component';
import { HttpClientModule} from '@angular/common/http';
import { TransportRouteComponent } from './transport-route/transport-route.component';
import { AddTransportRouteComponent } from './add-transport-route/add-transport-route.component';
import { AddVehicleComponent } from './add-vehicle/add-vehicle.component';
import { SubscriptionComponent } from './subscription/subscription.component';
import { VehicleComponent } from './vehicle/vehicle.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    AdminComponent,
    RegisterComponent,
    TransportRouteComponent,
    AddTransportRouteComponent,
    AddVehicleComponent,
    SubscriptionComponent,
    VehicleComponent,
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        ReactiveFormsModule,
        HttpClientModule,
        FormsModule
    ],
  providers: [

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
