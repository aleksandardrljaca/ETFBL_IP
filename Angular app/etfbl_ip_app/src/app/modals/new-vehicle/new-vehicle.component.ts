import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { VehicleService } from '../../services/vehicle-service/vehicle.service';
import { FormControl, Validators } from '@angular/forms';
import { NotifyService } from '../../services/notify-service/notify.service';
import { ImageService } from '../../services/image-service/image.service';

@Component({
  selector: 'app-new-vehicle',
  templateUrl: './new-vehicle.component.html',
  styleUrl: './new-vehicle.component.css'
})
export class NewVehicleComponent {
  ids:string[]=[];
  type:string='';
  id = new FormControl('', [Validators.required]);
  acquisitionDate = new FormControl('', [Validators.required,Validators.pattern('yyyy-mm-dd')]);
  acquisitionPrice= new FormControl('', [Validators.required]);
  manufacturerFormControl = new FormControl('');
  model = new FormControl('', [Validators.required]);
  description = new FormControl('', [Validators.required]);
  maxSpeed = new FormControl('', [Validators.required]);
  range = new FormControl('', [Validators.required]);
  selectedFileName:string|null=null;
  selectedFile:File|null=null;
  manufacturers:any[]=[];
 constructor(
  @Inject(MAT_DIALOG_DATA) public data:any,
  public dialogRef: MatDialogRef<NewVehicleComponent>,
  public vehicleService:VehicleService,
  public imageService:ImageService,
  public notifyService:NotifyService
 ){
    this.ids=data.vids;
    this.type=data.type;
    this.manufacturers=data.manufacturers;
 }
 isCar():boolean{
  return this.type=='CAR';
 }
 isScooter():boolean{
  return this.type=='ELECTRIC_SCOOTER';
 }
 isBicycle():boolean{
  return this.type=='ELECTRIC_BICYCLE';
 }
 onAdd():void{
    if(this.isFormValid()){
      var manuf:any;
      if(this.manufacturerFormControl.value)
        manuf=this.manufacturerFormControl.value;
      const formData = new FormData();
      if(this.selectedFile){
        formData.append('file', this.selectedFile);
      
        this.imageService.insertImage(formData).subscribe({next:(img:any)=>{
          this.insertVehicle(manuf,img);
        },error:(err)=>console.log(err)});
        
      } else this.notifyService.openSnackBar('Data not entered correctly!','Close');
    
    }else this.notifyService.openSnackBar('Data not entered correctly!','Close');
    
  
 }
 insertVehicle(manuf:any,image:any){
  const vehicle:any={id:this.id.value,acquisitionDate:this.acquisitionDate.value,acquisitionPrice:Number(this.acquisitionPrice.value),
    manufacturerByManufacturerIdId:manuf.id,model:this.model.value,description:this.description.value,vehicleType:this.type,
    maxSpeed:Number(this.maxSpeed.value),rangePerCharge:Number(this.range.value),imageByImageIdId:Number(image.id)
  };
  
  this.vehicleService.addVehicle(vehicle).subscribe({
    next:()=>{
      this.dialogRef.close();
      this.notifyService.openSnackBar('You added a new vehicle successfully!','Close');
    },
    error:(err)=>{
      this.dialogRef.close();
      this.notifyService.openSnackBar('An error occurred!','Close');
    }
  });
 }
 isFormValid():boolean{
    if(this.idExists() && !this.acquisitionPrice.valid && !this.model.valid){
      if(this.type=='CAR' && !this.acquisitionDate.valid && !this.description.valid)
        return true;
      else if(this.type=='ELECTRIC_SCOOTER' && !this.maxSpeed.valid)
        return false;
      else if(this.type=='ELECTRIC_BICYCLE' && !this.range.valid)
        return false;
    }
      
    return  true;
 }
 idExists():boolean{
  var id=''
  if(this.id.value)
    id=this.id.value;
  return this.ids.some(v=>v==id);
 }
 onClose():void{
  this.dialogRef.close();
}
onFileSelected(event: Event): void {
  const input = event.target as HTMLInputElement;
  if (input.files && input.files.length > 0) {
    this.selectedFileName = input.files[0].name;
    this.selectedFile=input.files[0];
  }
}

triggerFileInput(): void {
  const fileInput = document.getElementById('image-upload') as HTMLElement;
  fileInput.click();
}
}
