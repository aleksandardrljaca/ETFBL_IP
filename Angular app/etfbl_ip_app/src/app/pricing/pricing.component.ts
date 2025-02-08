import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { PricingService } from '../services/pricing-service/pricing.service';
import { NotifyService } from '../services/notify-service/notify.service';
import { MatDialog } from '@angular/material/dialog';
import { NewPricingComponent } from '../modals/new-pricing/new-pricing.component';

@Component({
  selector: 'app-pricing',
  templateUrl: './pricing.component.html',
  styleUrl: './pricing.component.css'
})
export class PricingComponent implements OnInit{
  displayedColumns=['from','to','price','update','delete'];
  pricing:any[]=[]
  pricingDataSource=new MatTableDataSource<any>(this.pricing);
  constructor(private pricingService:PricingService,private notifyService:NotifyService,private dialog:MatDialog){
   
  }
  @ViewChild(MatPaginator) paginator:MatPaginator
  ngOnInit(): void {
      this.loadData();
  }
  loadData(){
    this.pricingService.getAll().subscribe({
      next:(data)=>{
        this.pricing=data;
        this.pricingDataSource.data=this.pricing;
        this.pricingDataSource.paginator=this.paginator;
      },error:(err)=>console.log(err)
    })
  }
  onAddClick(){
   const dialogRef=this.dialog.open(NewPricingComponent,{
    data:null
   });
   dialogRef.afterClosed().subscribe(()=>this.loadData());
  }
  update(id:number){
    const price=this.pricing.find((p:any)=>p.id===id);
    const dialogRef=this.dialog.open(NewPricingComponent,{
      data:price
     });
     dialogRef.afterClosed().subscribe(()=>this.loadData());
  }
  delete(id:number){
    this.pricingService.delete(id).subscribe({
      next:()=>{
        this.loadData();
      },error:(err)=>this.notifyService.openSnackBar("Something went wrong!",'Close')
    })
  }
}
