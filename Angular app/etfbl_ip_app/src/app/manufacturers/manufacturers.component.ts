import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ManufacturerService } from '../services/manufacturer-service/manufacturer.service';
import { FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { NewManufacturerComponent } from '../modals/new-manufacturer/new-manufacturer.component';
import { NotifyService } from '../services/notify-service/notify.service';

@Component({
  selector: 'app-manufacturers',
  templateUrl: './manufacturers.component.html',
  styleUrl: './manufacturers.component.css'
})
export class ManufacturersComponent implements OnInit {
  manufacturers:any[]=[]
  manufacturersDataSource=new MatTableDataSource<any>(this.manufacturers);
  displayedColumns=['name','country','address','phone','fax','email','update','delete'];
  searchBar=new FormControl('');
  constructor(private mService:ManufacturerService,private dialog:MatDialog,private notifySerice:NotifyService ){
    this.searchBar.valueChanges.subscribe((newVal)=>{
        if(newVal=='')
          this.loadData();
        else {
          this.manufacturers=this.manufacturers.filter((m:any)=>m.country.includes(newVal));
          this.manufacturersDataSource.data=this.manufacturers;
          this.manufacturersDataSource.paginator=this.paginator;
        }
    });
  }
  @ViewChild(MatPaginator) paginator:MatPaginator;
  ngOnInit(): void {
      this.loadData();
  }
  loadData(){
    this.mService.getAll().subscribe({
      next:(data)=>{
        this.manufacturers=data;
        this.manufacturersDataSource.data=this.manufacturers;
        this.manufacturersDataSource.paginator=this.paginator;
      },
      error:(err)=>console.log(err)
    })
  }
  onAddClick(){
    const dialogRef=this.dialog.open(NewManufacturerComponent,{
      data:null
    });
    dialogRef.afterClosed().subscribe(()=>{
      this.loadData();
    });
  }
  update(id:number){
    const manufacturer=this.manufacturers.find((m:any)=>m.id===id);
    const dialogRef=this.dialog.open(NewManufacturerComponent,{
      data:manufacturer
    });
    dialogRef.afterClosed().subscribe(()=>{
      this.loadData();
    });
  }
  delete(id:number){
    this.mService.delete(id).subscribe({
      next:()=>{this.loadData();this.notifySerice.openSnackBar('Deleted!','Close');},
      error:err=>{console.log(err);this.notifySerice.openSnackBar('Cannot delete data!','Close');}});
    
  }

}
