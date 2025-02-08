import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ClientService } from '../services/client-service/client.service';
import { NotifyService } from '../services/notify-service/notify.service';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrl: './clients.component.css'
})
export class ClientsComponent implements OnInit{
  displayedColumns=['username','firstname','lastname','drivingLicense','idCard','email','phone','block'];
  clients:any[]=[]
  clientsDataSource=new MatTableDataSource<any>(this.clients);
  @ViewChild(MatPaginator) paginator:MatPaginator;
  searchBar=new FormControl('');
  constructor(private clientService:ClientService,private notifyService:NotifyService){
    this.searchBar.valueChanges.subscribe((newVal)=>{
        if(newVal=='')
          this.loadData();
        else {
          this.clients=this.clients.filter((c:any)=>c.firstname.includes(newVal));
          this.clientsDataSource.data=this.clients;
          this.clientsDataSource.paginator=this.paginator;
        }
    });
  }
  ngOnInit(): void {
    this.loadData();
  }
  loadData(){
    this.clientService.getAll().subscribe({
      next:(data)=>{
        this.clients=data;
        this.clientsDataSource.data=this.clients;
        this.clientsDataSource.paginator=this.paginator;
      },error:(err)=>console.log(err)
    });
  }
  block(id:number,event:any){
    const isChecked=event.target.checked;
    this.clientService.block(id,{block:isChecked}).subscribe({
        next:(data)=>{
          if(isChecked)
            this.notifyService.openSnackBar('User blocked!','Close');
          else this.notifyService.openSnackBar('User unblocked!','Close');
          this.loadData();
        },error:(err)=>console.log(err)
      });;
    
   
   
  }
  
}
