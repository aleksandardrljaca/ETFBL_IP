import { Component } from '@angular/core';
import { VehicleService } from '../services/vehicle-service/vehicle.service';

@Component({
  selector: 'app-vehicle-details',
  templateUrl: './vehicle-details.component.html',
  styleUrl: './vehicle-details.component.css'
})
export class VehicleDetailsComponent {
    vehicle:any;
    image: string | null = null;
    malfLbl:string='Malfunctions';
    rentsLbl:string='Rents';
    
   
    constructor(private vService:VehicleService){
      const id=localStorage.getItem('vehicleId');
      if(id)
       this.vService.getById(id).subscribe({
      next:(v:any)=>{
        this.vehicle=v;
        this.image="data:image/png;base64,"+this.vehicle.imageByImageIdImageData;
      },error:(err)=>{
        console.log(err);
      }});
    }
    isCar():boolean{
      return this.vehicle.vehicleType=="CAR";
    }
    isElectricScooter():boolean{
      return this.vehicle.vehicleType=="ELECTRIC_SCOOTER";
    }
    isElectricBicycle():boolean{
      return this.vehicle.vehicleType=="ELECTRIC_BICYCLE";
    }
   
}
