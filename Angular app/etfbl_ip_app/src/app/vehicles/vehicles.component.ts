import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { VehicleService } from '../services/vehicle-service/vehicle.service';
import {MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import { MatTableModule} from '@angular/material/table';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort, Sort } from '@angular/material/sort';
import { LiveAnnouncer } from '@angular/cdk/a11y';
import { NewVehicleComponent } from '../modals/new-vehicle/new-vehicle.component';
import { MatDialog } from '@angular/material/dialog';
import { MatTabChangeEvent } from '@angular/material/tabs';
import { ManufacturerService } from '../services/manufacturer-service/manufacturer.service';
import { NotifyService } from '../services/notify-service/notify.service';
import { Router } from '@angular/router';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-vehicles',
  templateUrl: './vehicles.component.html',
  styleUrls: ['./vehicles.component.css'],
})
export class VehiclesComponent implements OnInit{
  displayedColumnsCars: string[] = ['id','acquisitionDate','acquisitionPrice','manufacturerByManufacturerIdName','model','description','details','action'];
  displayedColumnsScooters: string[] = ['id','acquisitionPrice','manufacturerByManufacturerIdName','model','maxSpeed','details','action'];
  displayedColumnsBicycles: string[] = ['id','acquisitionPrice','model','manufacturerByManufacturerIdName','rangePerCharge','details','action'];
  cars:any[]=[];
  electricScooters:any[]=[];
  electricBicycles:any[]=[];
  carsDataSource = new MatTableDataSource<any>(this.cars);
  esDataSource = new MatTableDataSource<any>(this.electricScooters);
  ebDataSource = new MatTableDataSource<any>(this.electricBicycles);
  // tab active boolean value used to determine which Add New modal to open
  carsLbl:string='Cars';
  scootersLbl:string='Electric scooters';
  bicyclesLbl:string='Electric bicycles';
  carsActive:boolean=true;
  scootersActive:boolean=false;
  bicyclesActive:boolean=false;
  
  searchBar=new FormControl('');
  manufacturers:any[]=[];
  constructor(private vService:VehicleService,public dialog: MatDialog,private manufacturerService:ManufacturerService,private notifyService:NotifyService,private router:Router){
    this.searchBar.valueChanges.subscribe((newValue) => {
      if(this.searchBar.value=='')
        this.loadData();

      else{
        if(this.carsActive){
          this.cars=this.cars.filter((car:any)=>car.id.includes(newValue));
          this.carsDataSource.data=this.cars;
          this.carsDataSource.paginator=this.paginator;
        }
          
        else if(this.scootersActive){
          this.electricScooters=this.electricScooters.filter((scooter:any)=>scooter.id.includes(newValue));
          this.esDataSource.data=this.electricScooters;
          this.esDataSource.paginator=this.paginator;
        }
          
        else if(this.bicyclesActive){
          this.electricBicycles=this.electricBicycles.filter((bicycle:any)=>bicycle.id.includes(newValue));
          this.ebDataSource.data=this.electricBicycles;
          this.ebDataSource.paginator=this.paginator;
        }
          
      }
    });
  }
  @ViewChild(MatPaginator) paginator: MatPaginator;
  ngOnInit(): void {
      this.loadData();
      localStorage.removeItem('vehicleId');
  }
  loadData(){
    this.vService.getVehicles().subscribe({
      next:(data)=>{
        this.cars=data.filter((v:any)=>v.vehicleType=='CAR');
        this.electricScooters=data.filter((v:any)=>v.vehicleType=='ELECTRIC_SCOOTER');
        this.electricBicycles=data.filter((v:any)=>v.vehicleType=='ELECTRIC_BICYCLE');
        this.carsDataSource.data=this.cars;
        this.carsDataSource.paginator=this.paginator;
        this.esDataSource.data=this.electricScooters;
        this.esDataSource.paginator=this.paginator;
        this.ebDataSource.data=this.electricBicycles;
        this.ebDataSource.paginator=this.paginator;
      },
      error:(err)=>{
        console.log(err);
      }
    });
    this.manufacturerService.getAll().subscribe({
      next:(data)=>{
        this.manufacturers=data;
      },
      error:(err)=>{
        console.log(err);
      }
    })
  }
  delete(id:any):void{
    this.vService.delete(id).subscribe({
      next:()=>{
        this.cars=this.cars.filter((v:any)=>v.id!=id);
        this.carsDataSource.data=this.cars;
        this.electricScooters=this.electricScooters.filter((v:any)=>v.id!=id);
        this.esDataSource.data=this.electricScooters;
        this.electricBicycles=this.electricBicycles.filter((v:any)=>v.id!=id);
        this.ebDataSource.data=this.electricBicycles;
        this.notifyService.openSnackBar('Vehicle deleted!','Close');
      },
      error:(err)=>{
        if(err.status==403)
          this.notifyService.openSnackBar('Cannot be deleted! This data is referenced somewhere else!','Close');
        else console.log(err);
      }
    });
    
  }
  carsTab(){
    this.carsActive=true;
    this.scootersActive=false;
    this.bicyclesActive=false;
    console.log(this.carsActive+ ' '+this.scootersActive + ' '+this.bicyclesActive);
  }
  scootersTab(){
    this.carsActive=false;
    this.scootersActive=true;
    this.bicyclesActive=false;
    console.log(this.carsActive+ ' '+this.scootersActive + ' '+this.bicyclesActive);
  }
  bicyclesTab(){
    this.carsActive=false;
    this.scootersActive=false;
    this.bicyclesActive=true;
    console.log(this.carsActive+ ' '+this.scootersActive + ' '+this.bicyclesActive);
  }
  onTabClick(event: MatTabChangeEvent): void {
    if(event.tab.textLabel==this.carsLbl)
      this.carsTab();
    else if(event.tab.textLabel==this.scootersLbl)
      this.scootersTab();
    else if(event.tab.textLabel==this.bicyclesLbl)
      this.bicyclesTab();
  }
  openNewVehicleDialog(){
    console.log(this.scootersActive)
    var vtype='';
    if(this.carsActive)
      vtype='CAR';
    else if(this.scootersActive)
      vtype='ELECTRIC_SCOOTER';
    else vtype='ELECTRIC_BICYCLE';
    var data:any;
    const allVehicles: any[][] = [this.cars, this.electricScooters, this.electricBicycles];
    const allIds: string[] = allVehicles
                                      .flat()
                                      .map(item => item.id);  
    data={type:vtype,vids:allIds,manufacturers:this.manufacturers};
    const dialogRef=this.dialog.open(NewVehicleComponent,{
      data:data
    });
    dialogRef.afterClosed().subscribe((result) => {
      this.loadData();
    });
  }
  details(id:string){
    localStorage.setItem('vehicleId',id);
    this.router.navigate(['/vehicle-details']);
  }
  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const formData=new FormData();
      formData.append('file',input.files[0]);
      this.vService.insertFromCSV(formData).subscribe({
        next:(data)=>{
          this.notifyService.openSnackBar('Vehicles add successfully!','Close');
            this.loadData();
        }
      });
    }
  }
  
  triggerFileInput(): void {
    const fileInput = document.getElementById('csv-upload') as HTMLElement;
    fileInput.click();
  }
 
}

