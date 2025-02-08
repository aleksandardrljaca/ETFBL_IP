import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { EmployeeService } from '../../services/employee-service/employee.service';
import { NotifyService } from '../../services/notify-service/notify.service';

@Component({
  selector: 'app-new-employee',
  templateUrl: './new-employee.component.html',
  styleUrl: './new-employee.component.css'
})
export class NewEmployeeComponent implements OnInit {
  firstname=new FormControl('',Validators.required);
  lastname=new FormControl('',Validators.required);
  username=new FormControl('',Validators.required);
  password=new FormControl('',Validators.required);
  selectedRole:string=''
  
  constructor(@Inject(MAT_DIALOG_DATA) public data:any,public dialogRef:MatDialogRef<NewEmployeeComponent>,private eService:EmployeeService,private notifyService:NotifyService){

  }
  ngOnInit(): void {
    if(this.data!=null){
      var btn=document.getElementById('addBtn');
      if(btn)
        btn.innerHTML='Update';
      this.firstname.setValue(this.data.firstname);
      this.lastname.setValue(this.data.lastname);
      this.username.setValue(this.data.username);
      this.password.setValue(this.data.password);
      this.selectedRole=this.data.role;
     
    }
    
}
  onAdd(){
    if(this.firstname.valid && this.lastname.valid && this.username.valid
      && this.selectedRole!=''){
       const employee={firstname:this.firstname.value,lastname:this.lastname.value,username:this.username.value,password:this.password.value,role:this.selectedRole};
       
       //update
       if(this.data!=null){
         this.eService.update(this.data.id,employee).subscribe({
          
           next:(data)=>{this.dialogRef.close();this.notifyService.openSnackBar('Update successfull!','Close')},
           error:(err)=>console.log(err)
         });

       }
       //add
       else {
         this.eService.insert(employee).subscribe({
           next:(data)=>{
            this.dialogRef.close();
             this.notifyService.openSnackBar('Employee added successfully!','Close');
           },error:(err)=>console.log(err)
         });
       }
       
      }else this.notifyService.openSnackBar('Data not entered correctly!','Close');
  }
}
