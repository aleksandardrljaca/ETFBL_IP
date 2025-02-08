import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RentService {
  baseUrl:string='http://localhost:8000/api/rents';
  constructor(private http:HttpClient) { }

  getAll(){
    return this.http.get<any>(this.baseUrl);
  }
  getCurrentRents(){
    return this.http.get<any>(this.baseUrl+'/current-rents');
  }
  getRentsIncomePerDays(month:number){
    return this.http.get<any>(this.baseUrl+'/income-month/'+month);
  }
  getRentsIncomePerVehicleType(){
    return this.http.get<any>(this.baseUrl+'/income-vehicle-type');
  }
}
