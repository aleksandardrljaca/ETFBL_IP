import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user-service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit{
  constructor(private userService:UserService,private router:Router){}
  public a:boolean=false;
  public m:boolean=false;
  public o:boolean=false;
    ngOnInit(): void {
      this.checkRole();
    }
    vehicles(){
      this.router.navigate(['/vehicles']);
    }
    manufacturers(){
      this.router.navigate(['/manufacturers']);
    }
    users(){
      this.router.navigate(['/clients-employees']);
    }
    clients(){
      this.router.navigate(['/clients']);
    }
    malfunctions(){
      this.router.navigate(['/malfunctions']);
    }
    rentals(){
      this.router.navigate(['/rents']);
    }
    map(){
      this.router.navigate(['/map']);
    }
    statistics(){
      this.router.navigate(['/stats']);
    }
    pricing(){
      this.router.navigate(['/pricing']);
    }
    logout(){
      this.router.navigate(['/login']);
      localStorage.clear();
    }
    checkRole(){
      const usr=this.userService.getUser();
      if(usr){
        const data=usr;
        switch(data.role){
          case 'ADMIN':
            this.a=true;
            break;
          case 'OPERATOR':
            this.o=true;
            break;
          case 'MANAGER':
            this.m=true;
            break;
          
          default:
            break;
        }
      }
    }
}
