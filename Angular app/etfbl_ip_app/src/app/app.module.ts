import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { VehiclesComponent } from './vehicles/vehicles.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { MaterialModule } from './material/material.module';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule,provideHttpClient,withFetch} from '@angular/common/http';
import { HeaderComponent } from './shared/header/header.component';
import { NewVehicleComponent } from './modals/new-vehicle/new-vehicle.component';
import { VehicleDetailsComponent } from './vehicle-details/vehicle-details.component';
import { RentsComponent } from './rents/rents.component';
import { MalfunctionsComponent } from './malfunctions/malfunctions.component';
import { RentsPageComponent } from './rents-page/rents-page.component';
import { NewMalfunctionComponent } from './modals/new-malfunction/new-malfunction.component';
import { MalfunctionsPageComponent } from './malfunctions-page/malfunctions-page.component';
import { ManufacturersComponent } from './manufacturers/manufacturers.component';
import { NewManufacturerComponent } from './modals/new-manufacturer/new-manufacturer.component';
import { ClientsComponent } from './clients/clients.component';
import { EmployeesComponent } from './employees/employees.component';
import { ClientsPageComponent } from './clients-page/clients-page.component';
import { NewEmployeeComponent } from './modals/new-employee/new-employee.component';
import { ClientsEmployeesPageComponent } from './clients-employees-page/clients-employees-page.component';
import { PricingComponent } from './pricing/pricing.component';
import { NewPricingComponent } from './modals/new-pricing/new-pricing.component';
import { VehicleMapComponent } from './vehicle-map/vehicle-map.component';
import { StatsPageComponent } from './stats-page/stats-page.component';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { LoginComponent } from './login/login.component';



@NgModule({
  declarations: [
    AppComponent,
    VehiclesComponent,
    HeaderComponent,
    NewVehicleComponent,
    VehicleDetailsComponent,
    RentsComponent,
    MalfunctionsComponent,
    RentsPageComponent,
    NewMalfunctionComponent,
    MalfunctionsPageComponent,
    ManufacturersComponent,
    NewManufacturerComponent,
    ClientsComponent,
    EmployeesComponent,
    ClientsPageComponent,
    NewEmployeeComponent,
    ClientsEmployeesPageComponent,
    PricingComponent,
    NewPricingComponent,
    VehicleMapComponent,
    StatsPageComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxChartsModule

  ],
  providers: [
    provideClientHydration(),
    provideAnimationsAsync(),
    provideHttpClient(withFetch())
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
