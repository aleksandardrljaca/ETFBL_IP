import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VehiclesComponent } from './vehicles/vehicles.component';
import { VehicleDetailsComponent } from './vehicle-details/vehicle-details.component';
import { RentsPageComponent } from './rents-page/rents-page.component';
import { MalfunctionsPageComponent } from './malfunctions-page/malfunctions-page.component';
import { ManufacturersComponent } from './manufacturers/manufacturers.component';
import { ClientsComponent } from './clients/clients.component';
import { ClientsPageComponent } from './clients-page/clients-page.component';
import { ClientsEmployeesPageComponent } from './clients-employees-page/clients-employees-page.component';
import { PricingComponent } from './pricing/pricing.component';
import { VehicleMapComponent } from './vehicle-map/vehicle-map.component';
import { StatsPageComponent } from './stats-page/stats-page.component';
import { LoginComponent } from './login/login.component';
import { roleGuard } from './guards/role-guard/role.guard';


const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'vehicles',
    component: VehiclesComponent,
    canActivate: [roleGuard],
    data: { roles: ['ADMIN', 'MANAGER'] }
  },
  {
    path: 'vehicle-details',
    component: VehicleDetailsComponent,
    canActivate: [roleGuard],
    data: { roles: ['ADMIN', 'MANAGER'] }
  },
  {
    path: 'rents',
    component: RentsPageComponent,
    canActivate: [roleGuard],
    data: { roles: ['MANAGER', 'OPERATOR'] }
  },
  {
    path: 'malfunctions',
    component: MalfunctionsPageComponent,
    canActivate: [roleGuard],
    data: { roles: ['MANAGER', 'OPERATOR'] }
  },
  {
    path: 'manufacturers',
    component: ManufacturersComponent,
    canActivate: [roleGuard],
    data: { roles: ['ADMIN', 'MANAGER'] }
  },
  {
    path: 'clients',
    component: ClientsPageComponent,
    canActivate: [roleGuard],
    data: { roles: ['MANAGER', 'OPERATOR'] }
  },
  {
    path: 'clients-employees',
    component: ClientsEmployeesPageComponent,
    canActivate: [roleGuard],
    data: { roles: ['ADMIN', 'MANAGER'] }
  },
  {
    path: 'pricing',
    component: PricingComponent,
    canActivate: [roleGuard],
    data: { roles: ['MANAGER'] }
  },
  {
    path: 'map',
    component: VehicleMapComponent,
    canActivate: [roleGuard],
    data: { roles: ['MANAGER', 'OPERATOR'] }
  },
  {
    path: 'stats',
    component: StatsPageComponent,
    canActivate: [roleGuard],
    data: { roles: ['MANAGER'] }
  },
  {
    path: '',
    component: LoginComponent,
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
