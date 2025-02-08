import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { NotifyService } from '../services/notify-service/notify.service';
import { UserService } from '../services/user-service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
    username=new FormControl('',Validators.required);
    password=new FormControl('',Validators.required);
    constructor(private userService:UserService,private notifySerivce:NotifyService,private router:Router){}

    login(){
      if(this.username.valid && this.password.valid){
        console.log(this.username.value+" "+this.password.value)
        this.userService.login({username:this.username.value,password:this.password.value}).subscribe({
          next:(data:any)=>{
            localStorage.setItem('user',JSON.stringify(data));
            console.log(this.userService.isManager()+'JE MANAJGER')
            if(this.userService.isAdmin() || this.userService.isManager())
              this.router.navigate(['/vehicles']);
            else if(this.userService.isOperator())
              this.router.navigate(['/rents']);
          },error:(err)=>{
            if(err.status==404)
              this.notifySerivce.openSnackBar('Wrong credentials!','Close');
            else this.notifySerivce.openSnackBar('Something went wrong!','Close');
            
          }
        })
      }
    }
}
