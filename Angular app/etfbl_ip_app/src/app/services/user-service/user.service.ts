import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl:string='http://localhost:8000/api/login';
  constructor(private http:HttpClient) { }
  getUser():any{
    const data=localStorage.getItem('user');
    if(data)
      return JSON.parse(data);
    return null;
    
  }
  login(data:any){
    return this.http.post(this.baseUrl,data);
  }
  isAdmin():boolean{
    const user=this.getUser();
    return user.role=="ADMIN";
  }
  isOperator():boolean{
    const user=this.getUser();
    return user.role=="OPERATOR";
  }
  isManager():boolean{
    const user=this.getUser();
    return user.role=="MANAGER";
  }
  isCurrentlyLogged(id:number):boolean{
    const user=this.getUser();
    return user.id==id;
  }
}
