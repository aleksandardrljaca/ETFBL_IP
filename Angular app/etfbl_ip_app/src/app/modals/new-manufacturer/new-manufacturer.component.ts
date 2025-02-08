import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ManufacturerService } from '../../services/manufacturer-service/manufacturer.service';
import { NotifyService } from '../../services/notify-service/notify.service';

@Component({
  selector: 'app-new-manufacturer',
  templateUrl: './new-manufacturer.component.html',
  styleUrl: './new-manufacturer.component.css'
})
export class NewManufacturerComponent implements OnInit{
  name=new FormControl('',Validators.required);
  country=new FormControl('',Validators.required);
  address=new FormControl('',Validators.required);
  phone=new FormControl('',Validators.required);
  fax=new FormControl('',Validators.required);
  email=new FormControl('',Validators.required);
  constructor( @Inject(MAT_DIALOG_DATA) public data:any,
  public dialogRef: MatDialogRef<NewManufacturerComponent>,
  private mService:ManufacturerService,
  private notifySerivce:NotifyService
){}
ngOnInit(): void {
    if(this.data!=null){
      var btn=document.getElementById('addBtn');
      if(btn)
        btn.innerHTML='Update';
      this.name.setValue(this.data.name);
      this.country.setValue(this.data.country);
      this.address.setValue(this.data.address);
      this.phone.setValue(this.data.phone);
      this.fax.setValue(this.data.fax);
      this.email.setValue(this.data.email);
      console.log(JSON.stringify(this.data));
    }
    
}
  onAdd(){
    if(this.name.valid && this.country.valid && this.address.valid
       && this.phone.valid && this.fax.valid && this.email.valid){
        const manufacturer={name:this.name.value,country:this.country.value,
          address:this.address.value,phone:this.phone.value,fax:this.fax.value,email:this.email.value};
          console.log(manufacturer)
        //update
        if(this.data!=null){
          this.mService.update(this.data.id,manufacturer).subscribe({
            next:(data)=>this.notifySerivce.openSnackBar('Update successfull!','Close'),
            error:(err)=>console.log(err)
          });

        }
        //add
        else {
          this.mService.insert(manufacturer).subscribe({
            next:(data)=>{
              this.notifySerivce.openSnackBar('Manufacturer added successfully!','Close');
            },error:(err)=>console.log(err)
          });
        }
        this.dialogRef.close();
       }else this.notifySerivce.openSnackBar('Data not entered correctly!','Close');
       
  }
}
