import { Component, OnInit, ViewChild } from '@angular/core';
import { RentService } from '../services/rent-service/rent.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { VehicleService } from '../services/vehicle-service/vehicle.service';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-rents',
  templateUrl: './rents.component.html',
  styleUrl: './rents.component.css'
})
export class RentsComponent implements OnInit{
  displayedColumns=['date','startTime','endTime','locationByStartLocation','locationByEndLocation',
    'price','clientByClientIdName','paymentInfo','vehicleByVehicleId','vehicleManufacturer','vehicleModel'];
  rents:any[]=[]
  rentsDataSource=new MatTableDataSource<any>(this.rents);
  isVehicleDetailsPage:boolean=false;
  detailsVehicleId:string;
  searchBar=new FormControl('');
  @ViewChild(MatPaginator) paginator: MatPaginator;
  constructor
  (private rentService:RentService,
   private vService:VehicleService
  ){
    this.searchBar.valueChanges.subscribe((newValue)=>{
        if(newValue=='')
          this.loadData();
        else {
          this.rents=this.rents.filter((rent:any)=>rent.clientByClientIdFirstname.includes(newValue));
          this.rentsDataSource.data=this.rents;
          this.rentsDataSource.paginator=this.paginator;
        }
    });
  }
  ngOnInit(): void {
      var vehicleId:string=this.vService.getCurrentVehicle();
      if(vehicleId){
        this.displayedColumns.splice(8,3);
        this.isVehicleDetailsPage=true;
        this.detailsVehicleId=vehicleId;
      }
        
      this.loadData();
  }
  loadData():void{
    this.rentService.getAll().subscribe({
      next:(data)=>{
        if(!this.isVehicleDetailsPage)
            this.rents=data;
        else this.rents=data.filter((r:any)=>r.vehicleByVehicleIdId===this.detailsVehicleId);
        this.rentsDataSource.data=this.rents;
        this.rentsDataSource.paginator=this.paginator;
      }
    })
  }
}
