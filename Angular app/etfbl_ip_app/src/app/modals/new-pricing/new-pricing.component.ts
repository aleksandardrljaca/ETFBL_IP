import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { PricingService } from '../../services/pricing-service/pricing.service';
import { NotifyService } from '../../services/notify-service/notify.service';

@Component({
  selector: 'app-new-pricing',
  templateUrl: './new-pricing.component.html',
  styleUrl: './new-pricing.component.css'
})
export class NewPricingComponent implements OnInit{
  min=new FormControl('',Validators.required);
  max=new FormControl('',Validators.required);
  price=new FormControl('',Validators.required);

  
  constructor(@Inject(MAT_DIALOG_DATA) public data:any,public dialogRef:MatDialogRef<NewPricingComponent>,private pricingService:PricingService,private notifyService:NotifyService){

  }
  ngOnInit(): void {
    if(this.data!=null){
      var btn=document.getElementById('addBtn');
      if(btn)
        btn.innerHTML='Update';
      this.min.setValue(this.data.minDistance);
      this.max.setValue(this.data.maxDistance);
      this.price.setValue(this.data.price);
    }
    
}
  onAdd(){
    if(this.min.valid && this.max.valid && this.price.valid){
      const pricing={minDistance:this.min.value,maxDistance:this.max.value,price:this.price.value};
       
       //update
       if(this.data!=null){
         this.pricingService.update(this.data.id,pricing).subscribe({
           next:(data)=>this.notifyService.openSnackBar('Update successfull!','Close'),
           error:(err)=>console.log(err)
         });

       }
       //add
       else {
         this.pricingService.insert(pricing).subscribe({
           next:(data)=>{
             this.notifyService.openSnackBar('Pricing added successfully!','Close');
           },error:(err)=>console.log(err)
         });
       }
       this.dialogRef.close();
      }else this.notifyService.openSnackBar('Data not entered correctly!','Close');
  }
}
