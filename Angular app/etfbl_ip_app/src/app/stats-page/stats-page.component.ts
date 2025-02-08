import {  ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { MalfunctionService } from '../services/malfunction-service/malfunction.service';
import { RentService } from '../services/rent-service/rent.service';
@Component({
  selector: 'app-stats-page',
  templateUrl: './stats-page.component.html',
  styleUrl: './stats-page.component.css'
})
export class StatsPageComponent implements OnInit{
 months=[{num:1,month:'January'},{num:2,month:'February'},{num:3,month:'March'},{num:4,month:'April'},{num:5,month:'May'},{num:6,month:'June'},{num:7,month:'July'}
  ,{num:8,month:'August'},{num:9,month:'September'},{num:10,month:'October'},{num:11,month:'November'},{num:12,month:'December'}]
  monthsNum=[
    { month: 'January', number: 1 },
    { month: 'February', number: 2 },
    { month: 'March', number: 3 },
    { month: 'April', number: 4 },
    { month: 'May', number: 5 },
    { month: 'June', number: 6 },
    { month: 'July', number: 7 },
    { month: 'August', number: 8 },
    { month: 'September', number: 9 },
    { month: 'October', number: 10 },
    { month: 'November', number: 11 },
    { month: 'December', number: 12 },
  ];
  
 malfunctions:any[]=[]
 mXLbl="Vehicle ID"
 mYLbl="Num of malfunctions"
 dayXLbl="Day #"
 dayYLbl="Total income"
 typeXLbl="Vehicle type"
 typeYLbl="Total income"
 rentIncomePerDays:any[]=[]
 rentIncomePerVehicleType:any[]=[]
 colorScheme:any= {
  domain: ['#5AA454', '#A10A28', '#C7B42C', '#AAAAAA']
};
 constructor(private mService:MalfunctionService,private rService:RentService,private cdRef:ChangeDetectorRef){
 }
 ngOnInit(): void {
  this.mService.getNumberOfMalfunctionsPerVehicle().subscribe({
    next:(data)=>{this.malfunctions=data.map((item:any)=> ({
      name: item.vehicleId,
      value: item.num,
    }));;console.log(JSON.stringify(this.malfunctions))},
    error:(err)=>console.log(err)
   });
   this.rService.getRentsIncomePerDays(1).subscribe({
    next:(data)=>{this.rentIncomePerDays=data.map((item:any)=> ({
      name: item.day,
      value: item.total,
    }));this.cdRef.detectChanges();console.log(JSON.stringify(this.rentIncomePerDays))},
    error:(err)=>console.log(err)
   });
   this.rService.getRentsIncomePerVehicleType().subscribe({
    next:(data)=>this.rentIncomePerVehicleType=data.map((item:any)=> ({
      name: item.vehicleType,
      value: item.total,
    })),
    error:(err)=>console.log(err)
   });
 }
 onSelectionChange(event:any){
  const monthNumber = this.monthsNum.find((item:any) => item.month ==event.value.month)?.number;
  if(monthNumber){
    this.rService.getRentsIncomePerDays(monthNumber).subscribe({
      next:(data)=>{this.rentIncomePerDays=data.map((item:any)=> ({
        name: item.day,
        value: item.total,
      }));this.cdRef.detectChanges();console.log(JSON.stringify(this.rentIncomePerDays))},
      error:(err)=>console.log(err)
     });
  }else console.log("An error occurred!");
 }
}
