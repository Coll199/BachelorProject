import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './home/home.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {AdminComponent} from './admin/admin.component';
import {AuthGuard} from './guards/auth.guard';
import {TransportRouteComponent} from './transport-route/transport-route.component';
import {AddTransportRouteComponent} from './add-transport-route/add-transport-route.component';
import {SubscriptionComponent} from './subscription/subscription.component';
import {AdminGuard} from './guards/admin.guard';
import {AddVehicleComponent} from './add-vehicle/add-vehicle.component';
import {VehicleComponent} from './vehicle/vehicle.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [AdminGuard]
  },
  { path: 'route/:routeId',
    component: TransportRouteComponent
  },
  { path: 'addRoute',
    component: AddTransportRouteComponent,
    canActivate: [AuthGuard]
  },
  { path: 'subscription',
    component: SubscriptionComponent,
    canActivate: [AuthGuard]
  },
  { path: 'addVehicle',
    component: AddVehicleComponent,
    canActivate: [AdminGuard]
  },
  { path: 'vehicle/:vehicleId',
    component: VehicleComponent,
    canActivate: [AdminGuard]
  },
  // redirect to home otherwise
  { path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
