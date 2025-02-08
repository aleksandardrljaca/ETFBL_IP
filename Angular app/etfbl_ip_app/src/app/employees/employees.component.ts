import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { EmployeeService } from '../services/employee-service/employee.service';
import { NotifyService } from '../services/notify-service/notify.service';
import { FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { NewEmployeeComponent } from '../modals/new-employee/new-employee.component';
import { UserService } from '../services/user-service/user.service';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrl: './employees.component.css'
})
export class EmployeesComponent implements OnInit {
  displayedColumns=['username','firstname','lastname','role','update','delete'];
  employees:any[]=[]
  employeesDataSource=new MatTableDataSource<any>(this.employees);
  searchBar=new FormControl('');
  constructor(private eService:EmployeeService,private notifyService:NotifyService,private dialog:MatDialog,private userService:UserService){
    this.searchBar.valueChanges.subscribe((newVal)=>{
      if(newVal=='')
        this.loadData();
      else {
        this.employees=this.employees.filter((e:any)=>e.firstname.includes(newVal));
        this.employeesDataSource.data=this.employees;
        this.employeesDataSource.paginator=this.paginator;
      }
    })
  }
  @ViewChild(MatPaginator) paginator:MatPaginator
  ngOnInit(): void {
      this.loadData();
  }
  loadData(){
    this.eService.getAll().subscribe({
      next:(data)=>{
        this.employees=data;
        this.employeesDataSource.data=this.employees;
        this.employeesDataSource.paginator=this.paginator;
      },error:(err)=>console.log(err)
    })
  }
  onAddClick(){
    const dialogRef=this.dialog.open(NewEmployeeComponent,{
      data:null
    });
    dialogRef.afterClosed().subscribe(()=>{
      this.loadData();
    });
  }
  update(id:number){
    const employee=this.employees.find((e:any)=>e.id===id);
    const dialogRef=this.dialog.open(NewEmployeeComponent,{
      data:employee
    });
    dialogRef.afterClosed().subscribe(()=>{
      this.loadData();
    });
  }
  delete(id:number){
    this.eService.delete(id).subscribe({
      next:()=>{this.notifyService.openSnackBar('Employee deleted!','Close');this.loadData();},
      error:(err)=>console.log(err)
    });
    
  }
  isCurrentlyLogged(employeeId:number):boolean{
    return this.userService.isCurrentlyLogged(employeeId);
  }
}
