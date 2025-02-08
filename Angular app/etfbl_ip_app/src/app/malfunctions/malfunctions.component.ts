import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { VehicleService } from '../services/vehicle-service/vehicle.service';
import { MalfunctionService } from '../services/malfunction-service/malfunction.service';
import { MatDialog } from '@angular/material/dialog';
import { NewMalfunctionComponent } from '../modals/new-malfunction/new-malfunction.component';
import { FormControl } from '@angular/forms';
@Component({
  selector: 'app-malfunctions',
  templateUrl: './malfunctions.component.html',
  styleUrl: './malfunctions.component.css'
})
export class MalfunctionsComponent implements OnInit {
displayedColumns=['date','time','vehicleId','vehicleManufacturer','vehicleModel','description','action'];
  malfunctions:any[]=[]
  malfDataSource=new MatTableDataSource<any>(this.malfunctions);
  isVehicleDetailsPage:boolean=false;
  detailsVehicleId:string;
  searchBar=new FormControl('');
  @ViewChild(MatPaginator) paginator: MatPaginator;
  constructor
  (private mService:MalfunctionService,
   private vService:VehicleService,
   private dialog:MatDialog
  ){
    this.searchBar.valueChanges.subscribe((newValue) =>{
        if(newValue=='')
          this.loadData();
        else{
          if(this.isVehicleDetailsPage)
            this.malfunctions=this.malfunctions.filter((m:any)=>m.date.includes(newValue));
          else this.malfunctions=this.malfunctions.filter((m:any)=>m.vehicleByVehicleIdId.includes(newValue));
          this.malfDataSource.data=this.malfunctions
          this.malfDataSource.paginator=this.paginator;
        }
    });
  }
  ngOnInit(): void {
      var vehicleId:string=this.vService.getCurrentVehicle();
      if(vehicleId){
        this.displayedColumns.splice(2,3);
        this.isVehicleDetailsPage=true;
        this.detailsVehicleId=vehicleId;
      }
        
      this.loadData();
  }
  loadData():void{
    this.mService.getAll().subscribe({
      next:(data)=>{
        if(!this.isVehicleDetailsPage)
            this.malfunctions=data;
        else this.malfunctions=data.filter((r:any)=>r.vehicleByVehicleIdId===this.detailsVehicleId);
        //console.log(JSON.stringify(this.malfunctions))
        this.malfDataSource.data=this.malfunctions
        this.malfDataSource.paginator=this.paginator;
      },error:(err)=>console.log(err)
    })
  }
  onAddClick(){
    const dialogRef=this.dialog.open(NewMalfunctionComponent);
    dialogRef.afterClosed().subscribe((result) => {
      this.loadData();
    });
  }
  delete(id:number){
    this.mService.delete(id).subscribe({
      next:()=>this.loadData(),
      error:(err)=>console.log(err)
    });
    
  }
}
