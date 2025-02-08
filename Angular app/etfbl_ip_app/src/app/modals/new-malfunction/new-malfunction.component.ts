import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { NewVehicleComponent } from '../new-vehicle/new-vehicle.component';
import { MatDialogRef } from '@angular/material/dialog';
import { VehicleService } from '../../services/vehicle-service/vehicle.service';
import { MalfunctionService } from '../../services/malfunction-service/malfunction.service';
import { NotifyService } from '../../services/notify-service/notify.service';

@Component({
  selector: 'app-new-malfunction',
  templateUrl: './new-malfunction.component.html',
  styleUrl: './new-malfunction.component.css'
})
export class NewMalfunctionComponent implements OnInit{
    date=new FormControl('',Validators.required);
    time=new FormControl('',Validators.required);
    desc=new FormControl('',Validators.required);
    id=new FormControl('',Validators.required);
    constructor(public dialogRef: MatDialogRef<NewVehicleComponent>,private mService:MalfunctionService,private vService:VehicleService,private notifyService:NotifyService){}
    ngOnInit(): void {
        if(this.vService.getCurrentVehicle()){
          this.id.setValue(this.vService.getCurrentVehicle());
          //this.id.disable();
        }
    }
    onAdd(){
        if(this.date.valid && this.time.valid && this.desc.valid && this.id.valid){
          const data={date:this.date.value,time:this.time.value,description:this.desc.value,vehicleByVehicleIdId:this.id.value};
          this.mService.insert(data).subscribe({
            next:(data)=>this.notifyService.openSnackBar('Malfunction added successfully!','Close'),
            error:(err)=>console.log(err)
          });
          this.dialogRef.close();
        }else this.notifyService.openSnackBar('Data not entered correctly!','Close');
    }
}
